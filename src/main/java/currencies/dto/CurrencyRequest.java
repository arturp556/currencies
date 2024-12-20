package currencies.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CurrencyRequest(

    @NotBlank
    @Size(min=5, max=99)
    String name,

    @NotBlank
    @Size(min=3, max=3)
    String currency
) {}
