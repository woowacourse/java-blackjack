package balckjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @Test
    void createInstance() {
        Money money = new Money(1000);
        Assertions.assertThat(money).isInstanceOf(Money.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1000})
    void validateNotPositive(int input) {
        Assertions.assertThatThrownBy(() -> new Money(input))
            .isInstanceOf(IllegalArgumentException.class).hasMessage(String.format("베팅 금액은 0보다 커야 합니다. 들어온 금액 : %d", input));
    }
    @Nested
    class calculateMoney{
        @Test
        void ifWin(){
            Money money = new Money(1000);
            Assertions.assertThat(money.applyRate(Result.WIN)).isEqualTo(1000);
        }
        @Test
        void ifDraw(){
            Money money = new Money(1000);
            Assertions.assertThat(money.applyRate(Result.DRAW)).isEqualTo(0);
        }
        @Test
        void ifLose(){
            Money money = new Money(1000);
            Assertions.assertThat(money.applyRate(Result.LOSE)).isEqualTo(-1000);
        }
        @Test
        void ifBlackJack(){
            Money money = new Money(1000);
            Assertions.assertThat(money.applyRate(Result.BLACKJACK)).isEqualTo(1500);
        }
    }

}