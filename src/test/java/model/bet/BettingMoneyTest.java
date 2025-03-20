package model.bet;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.IllegalBlackjackInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingMoneyTest {

    @DisplayName("베팅 머니를 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {1000, 500_000, 1_000_000})
    void create(final int money) {
        assertThatCode(() -> new BettingMoney(money)).doesNotThrowAnyException();
    }

    @DisplayName("베팅 머니가 1000원 미만이거나 100만원 초과라면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {999, -1000, 1_000_001})
    void validateBettingMoneyRange(final int money) {
        assertThatThrownBy(() -> new BettingMoney(money))
                .isInstanceOf(IllegalBlackjackInputException.class);
    }

    @DisplayName("베팅 머니가 1000원 단위가 아니라면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {1001, 2002, 999_999})
    void validateBettingMoneyUnit(final int money) {
        assertThatThrownBy(() -> new BettingMoney(money))
                .isInstanceOf(IllegalBlackjackInputException.class);
    }
}
