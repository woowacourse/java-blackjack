package blackjack.domain;

import blackjack.domain.participants.GamerInformation.GamblingMoney;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class gamblingMoneyTest {
    @Test
    @DisplayName(("돈을 더한다."))
    void addTest() {
        GamblingMoney gamblingMoney = new GamblingMoney(1000);
        gamblingMoney = gamblingMoney.add(new GamblingMoney(3000));

        Assertions.assertThat(gamblingMoney.getValue()).isEqualTo(4000);
    }

    @Test
    @DisplayName(("돈을 뺀다."))
    void subtractTest() {
        GamblingMoney gamblingMoney = new GamblingMoney(3000);
        gamblingMoney = gamblingMoney.subtract(new GamblingMoney(1000));

        Assertions.assertThat(gamblingMoney.getValue()).isEqualTo(2000);
    }

    @Test
    @DisplayName(("돈을 곱한다."))
    void multiplyTest() {
        GamblingMoney gamblingMoney = new GamblingMoney(3000);
        gamblingMoney = gamblingMoney.multiply(2);

        Assertions.assertThat(gamblingMoney.getValue()).isEqualTo(6000);
    }
}
