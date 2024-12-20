package currencies.service.npb;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "currencies", url = "http://api.nbp.pl/api")
public interface NbpCurrencyClient {

    @RequestMapping(method = RequestMethod.GET, value = "/exchangerates/tables/A?format=json")
    List<CurrencyTable> getCurrenciesTable();

}