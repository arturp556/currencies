package currencies.service.repository;

import currencies.service.entity.CurrencyRequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRequestHistoryRepository extends JpaRepository<CurrencyRequestHistory, Long> {

}
