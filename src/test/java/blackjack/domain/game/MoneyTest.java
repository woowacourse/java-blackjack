package blackjack.domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @DisplayName("Money는 숫자가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "안녕", "123abc", "100-"})
    void throwsExceptionIfNotInteger(String input) {
        // when & then
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 정수만 가능합니다.");
    }
    
    @DisplayName("Money의 changeSign메서드는 Money의 부호를 바꿔서 반환한다.")
    @Test
    void returnsChangedSignAtMoney() {
        // given
        Money money = new Money("10000");
        
        // when
        Money changedSignMoney = money.changeSign();

        // then
        assertThat(changedSignMoney.getValue()).isEqualTo(money.getValue() * -1);
    }

    @DisplayName("Money의 makeZero메서드는 Money의 value를 0으로 바꾼다.")
    @Test
    void makeMoneyValueToZero() {
        // given
        Money money = new Money("10000");

        // when
        Money zeroMoney = money.makeZero();

        // then
        assertThat(zeroMoney.getValue()).isEqualTo(0);
    }
    
    @DisplayName("Money의 CalculateIfBlackJack은 Money의 value를 1.5배로 만든다.")
    @Test
    void makeMoneyOneAndHalfTimes() {
        // given
        Money money = new Money("10000");
        
        // when
        Money blackJackProfit = money.calculateIfBlackJack();

        // then
        assertThat(blackJackProfit.getValue()).isEqualTo((int) Math.floor(money.getValue() * 1.5));
    }
}
