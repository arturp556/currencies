package currencies.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.Instant;

public record CurrencyRequestHistoryDto(
    String currency,
    String name,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "CET") Instant date,
    BigDecimal value) {}
