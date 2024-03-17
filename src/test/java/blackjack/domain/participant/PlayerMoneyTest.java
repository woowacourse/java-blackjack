package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerMoneyTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new PlayerMoney(1000))
                .doesNotThrowAnyException();
    }

    @DisplayName("돈을 곱할 수 있다.")
    @Test
    void multiply() {
        PlayerMoney money = new PlayerMoney(1000);

        PlayerMoney result = money.multiply(1.5);

        assertThat(result.getAmount()).isEqualTo(1500);
    }

    @DisplayName("주어진 값보다 크거나 같은지 검사할 수 있다.")
    @Test
    void isGreaterEqual() {
        PlayerMoney money = new PlayerMoney(100);

        Assertions.assertAll(
                () -> Assertions.assertFalse(money.isGreaterEqualThan(101)),
                () -> Assertions.assertTrue(money.isGreaterEqualThan(100)),
                () -> Assertions.assertTrue(money.isGreaterEqualThan(99))
        );
    }
}
