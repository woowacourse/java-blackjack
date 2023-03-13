package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultMoneyTest {

    @Test
    @DisplayName("금액을 1.5배 늘린다.")
    void multiply() {
        ResultMoney resultMoney = new ResultMoney(1000);

        ResultMoney result = resultMoney.multiply(1.5);

        Assertions.assertThat(result).isEqualTo(new ResultMoney(1500));
    }

    @Test
    @DisplayName("금액에 마이너스부호를 붙인다.")
    void negative() {
        ResultMoney resultMoney = new ResultMoney(1000);

        ResultMoney result = resultMoney.negative();

        Assertions.assertThat(result).isEqualTo(new ResultMoney(-1000));
    }

}
