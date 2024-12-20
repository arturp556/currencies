package currencies.controller;

import currencies.exception.CurrencyException;
import currencies.service.CurrencyService;
import currencies.dto.CurrencyRequest;
import currencies.dto.CurrencyRequestHistoryDto;
import currencies.dto.CurrencyResponse;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("currencies")
class CurrencyController {

    private final CurrencyService currencyService;

    CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("requests")
    List<CurrencyRequestHistoryDto> getHistory() {
        return currencyService.getCurrencyRequestHistory();
    }

    @PostMapping("get-current-currency-value-command")
    CurrencyResponse handleRequest(@RequestBody @Valid CurrencyRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorsMessage = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining("\n"));
            throw new CurrencyException(errorsMessage);
        }
        return currencyService.handleCurrencyRequest(request);
    }
}
