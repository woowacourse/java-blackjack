package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingMoneyTest {
    @Test
    @DisplayName("생성")
    void create() {
        assertThatCode(() -> new BettingMoney("1000")).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("생성 - 음수가능")
    void create_minus() {
        assertThatCode(() -> new BettingMoney("-1000")).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("생성 실패 - 소수점 불가능")
    void create_fail() {
        assertThatThrownBy(()->new BettingMoney("-1.2")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("곱하기")
    void multiply() {
        final BettingMoney bettingMoney = new BettingMoney("1000");

        assertThat(bettingMoney.multiply(1.5)).isEqualTo(new BettingMoney(1500));

    }

}
