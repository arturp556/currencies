package currencies;

import currencies.dto.CurrencyRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CurrencyRequestValidatorTest {

    final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    @DisplayName("Valid currency request should not cause any violations")
    public void t1() {
//        given:
        final CurrencyRequest currencyRequest = new CurrencyRequest("Jan Kowalski", "USD");

//       when:
        final Set<ConstraintViolation<CurrencyRequest>> violations = validator.validate(currencyRequest);

//       then:
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Name should not be empty")
    public void t2() {
//        given:
        final CurrencyRequest currencyRequest = new CurrencyRequest("", "USD");

//        when:
        final Set<ConstraintViolation<CurrencyRequest>> violations = validator.validate(currencyRequest);

//       then:
        assertFalse(violations.isEmpty());
    }
}
