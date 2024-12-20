package currencies.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "currency_request_history")
@Entity
public class CurrencyRequestHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="currency_request_history_id", nullable = false)
    private Long currencyRequestHistoryId;

    @Column(name="currency", nullable = false)
    private String currency;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="date", nullable = false)
    private Instant date;

    @Column(name="currency_value", nullable = true)
    private BigDecimal value;
}
