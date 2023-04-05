import UnitExample.TaxesDeterminant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TaxesDeterminantTest {

    @Test
    public void taxEarningsMinusSpendingsSucces() {
        // given:
        int x = 3055, y = 1122, expected = 289;
        // when:
        int result = TaxesDeterminant.taxEarningsMinusSpendings(x, y);
        // then:
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void taxEarningsMinusSpendingsLessZero() {
        // given:
        int x = 55, y = 1122, expected = 0;
        // when:
        int result = TaxesDeterminant.taxEarningsMinusSpendings(x, y);

        // then:
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void taxEarningsSixPercentSucces() {
        // given:
        int x = 55, expected = 3;
        // when:
        int result = TaxesDeterminant.taxEarningsSixPercent(x);
        // then:
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void changeSystemEarn() {
        // given:
        int x = 9, y = 5;
        String expected = "Мы советуем вам УСН доходы";
        // when:
        String result = TaxesDeterminant.changeSystem(x, y);
        // then:
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void changeSystemSpendings() {
        // given:
        int x = 4, y = 11;
        String expected = "Мы советуем вам УСН доходы минус расходы";
        // when:
        String result = TaxesDeterminant.changeSystem(x, y);
        // then:
        Assertions.assertEquals(expected, result);
    }
    @Test
    public void equalToTax() {
        // given:
        int x = 1255, y = 33;
        // when:
        int resultOne = TaxesDeterminant.taxEarningsMinusSpendings(x, y);
        int resultTwo = TaxesDeterminant.taxEarningsSixPercent(x);
        // then:
        assertThat(resultOne, greaterThanOrEqualTo(resultTwo));
    }

    @Test
    public void equalToTaxNew() {
        // given:
        int x = 31, y = 312;
        String expected = "доходы минус расходы";
        // when:
        String result = TaxesDeterminant.changeSystem(x,y);
        // then:
        assertThat(result, containsString(expected));
    }
}