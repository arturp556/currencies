package currencies.service;

import currencies.dto.CurrencyRequest;
import currencies.dto.CurrencyRequestHistoryDto;
import currencies.dto.CurrencyResponse;
import currencies.exception.CurrencyException;
import currencies.exception.CurrencyNotFoundException;
import currencies.service.entity.CurrencyRequestHistory;
import currencies.service.npb.CurrencyTable;
import currencies.service.npb.NbpCurrencyClient;
import currencies.service.npb.Rate;
import currencies.service.repository.CurrencyRequestHistoryRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.Clock;
import java.time.Instant;
import java.util.List;

@Service
public class CurrencyService {

    private final CurrencyPersistanceService currencyPersistanceService;
    private final NbpCurrencyClient nbpCurrencyClient;
    private final CurrencyRequestHistoryRepository currencyRequestHistoryRepository;
    private final Clock clock;

    public CurrencyService(CurrencyPersistanceService currencyPersistanceService, NbpCurrencyClient nbpCurrencyClient, CurrencyRequestHistoryRepository currencyRequestHistoryRepository, Clock clock) {
        this.currencyPersistanceService = currencyPersistanceService;
        this.nbpCurrencyClient = nbpCurrencyClient;
        this.currencyRequestHistoryRepository = currencyRequestHistoryRepository;
        this.clock = clock;
    }

    public List<CurrencyRequestHistoryDto> getCurrencyRequestHistory() {
        return currencyRequestHistoryRepository.findAll().stream()
                .map(c -> new CurrencyRequestHistoryDto(c.getCurrency(), c.getName(), c.getDate(), c.getValue()))
                .toList();
    }

    @Transactional
    public CurrencyResponse handleCurrencyRequest(@NonNull final CurrencyRequest request) {
        CurrencyRequestHistory entity = new CurrencyRequestHistory();
        entity.setCurrency(request.currency());
        entity.setName(request.name());
        entity.setDate(Instant.now(clock));
        CurrencyRequestHistory persistedUntity = currencyPersistanceService.saveCurrencyRequest(entity);

        final List<CurrencyTable> currencyTable = nbpCurrencyClient.getCurrenciesTable();
        final CurrencyTable tableA = currencyTable.stream().filter(table -> table.table.equals("A")).findAny()
                .orElseThrow(() -> new CurrencyException("Currency table not found"));

        final Rate rate = tableA.rates.stream()
                .filter(r -> r.getCode()
                .equalsIgnoreCase(request.currency())).findAny()
                .orElseThrow(() -> new CurrencyNotFoundException(MessageFormat.format("Currency {0} not found", request.currency())));

        persistedUntity.setValue(rate.getMid());
        currencyRequestHistoryRepository.save(entity);
        return new CurrencyResponse(persistedUntity.getName(), persistedUntity.getCurrency(), persistedUntity.getValue());
    }
}
