package currencies.service.npb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyTable {
    public String table;
    public String no;
    public String effectiveDate;
    public List<Rate> rates = new ArrayList<>();
}


