package domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @DisplayName("Money 는  int 를 매개 변수로 받는다.")
    @Test
    void createMoneyTest() {
        assertThatCode(() -> new Money(10000)).doesNotThrowAnyException();
    }

    @DisplayName("돈은 0보다 큰 자연수 여야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -9999})
    void validateMoneyRange(int money) {
        assertThatThrownBy(() -> new Money(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("돈은 0보다 큰 자연수여야 합니다.");
    }

    @DisplayName("결과 비율에 맞추어 돈을 계산한다")
    @Test
    void getResultMoneyTest() {
        Money money = new Money(10000);
        PlayerResult blackJack = PlayerResult.BLACKJACKWIN;
        PlayerResult win = PlayerResult.WIN;
        PlayerResult draw = PlayerResult.DRAW;
        PlayerResult lose = PlayerResult.LOSE;

        assertAll(
                () -> assertThat(money.getResultMoney(blackJack)).isEqualTo(15000),
                () -> assertThat(money.getResultMoney(win)).isEqualTo(10000),
                () -> assertThat(money.getResultMoney(draw)).isEqualTo(0),
                () -> assertThat(money.getResultMoney(lose)).isEqualTo(-10000)
        );
    }
}
