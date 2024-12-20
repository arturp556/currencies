package currencies.dto;

import java.math.BigDecimal;

public record CurrencyResponse(
    String name,
    String currency,
    BigDecimal value) {
}


