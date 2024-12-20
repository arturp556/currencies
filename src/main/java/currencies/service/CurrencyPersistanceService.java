package currencies.service;

import currencies.service.entity.CurrencyRequestHistory;
import currencies.service.npb.NbpCurrencyClient;
import currencies.service.repository.CurrencyRequestHistoryRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.time.Clock;

@Service
public class CurrencyPersistanceService {

    private final CurrencyRequestHistoryRepository currencyRequestHistoryRepository;

    public CurrencyPersistanceService(CurrencyRequestHistoryRepository currencyRequestHistoryRepository) {
        this.currencyRequestHistoryRepository = currencyRequestHistoryRepository;
    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public CurrencyRequestHistory saveCurrencyRequest(@NonNull final CurrencyRequestHistory entity) {
        return currencyRequestHistoryRepository.save(entity);
    }

}
