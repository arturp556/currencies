package currencies;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import currencies.dto.CurrencyRequest;
import currencies.dto.CurrencyResponse;
import currencies.exception.CurrencyNotFoundException;
import currencies.service.CurrencyPersistanceService;
import currencies.service.CurrencyService;
import currencies.service.entity.CurrencyRequestHistory;
import currencies.service.npb.CurrencyTable;
import currencies.service.npb.NbpCurrencyClient;
import currencies.service.repository.CurrencyRequestHistoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CurrencyServiceTest {

    final ObjectMapper mapper = new ObjectMapper();
    final URL tablesUrl = getClass().getClassLoader().getResource("tables.json");

    final Clock clock = Clock.fixed(Instant.parse("2007-12-03T10:15:30.00Z"), ZoneId.systemDefault());
    final CurrencyRequestHistoryRepository currencyRequestHistoryRepositoryMock = Mockito.mock(CurrencyRequestHistoryRepository.class);
    final CurrencyPersistanceService currencyPersistanceServiceMock = Mockito.mock(CurrencyPersistanceService.class);
    final NbpCurrencyClient nbpCurrencyClientMock = Mockito.mock(NbpCurrencyClient.class);
    final CurrencyService currencyService = new CurrencyService(currencyPersistanceServiceMock, nbpCurrencyClientMock, currencyRequestHistoryRepositoryMock, clock);

    @Test
    @DisplayName("Should return current currency value")
    public void shouldReturnCurrentCurrencyValue() throws IOException {
//        given:
        final List<CurrencyTable> npbResponse = mapper.readValue(tablesUrl, new TypeReference<List<CurrencyTable>>() {});
        when(nbpCurrencyClientMock.getCurrenciesTable())
            .thenReturn(npbResponse);
        when(currencyPersistanceServiceMock.saveCurrencyRequest(any()))
            .thenReturn(new CurrencyRequestHistory(1L, "USD", "Jan Kowalski", Instant.parse("2007-12-03T10:15:30.00Z"), new BigDecimal("4.0623")));

        final CurrencyRequest currencyRequest = new CurrencyRequest("Jan Kowalski", "USD");

//      when:
        final CurrencyResponse currencyResponse = currencyService.handleCurrencyRequest(currencyRequest);

//      then:
        assertEquals(new BigDecimal("4.0623"), currencyResponse.value());
    }

    @Test
    @DisplayName("Should throw CurrencyNotFoundException for not existing currency")
    public void shouldThrowCurrencyNotFoundExceptionForNotExistingCurrency() throws IOException {
//        given:
        final List<CurrencyTable> npbResponse = mapper.readValue(tablesUrl, new TypeReference<List<CurrencyTable>>() {});
        when(nbpCurrencyClientMock.getCurrenciesTable())
                .thenReturn(npbResponse);
        when(currencyPersistanceServiceMock.saveCurrencyRequest(any()))
                .thenReturn(new CurrencyRequestHistory(1L, "ABC", "Jan Kowalski", Instant.parse("2007-12-03T10:15:30.00Z"), null));

        final CurrencyRequest currencyRequest = new CurrencyRequest("Jan Kowalski", "ABC");

//      then:
        assertThrows(CurrencyNotFoundException.class, () -> {
            currencyService.handleCurrencyRequest(currencyRequest);
        });
    }
}
