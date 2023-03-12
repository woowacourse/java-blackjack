package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.game.exception.InvalidMoneyValueException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingMoneyTest {

    @Test
    @DisplayName("베팅 금액을 생성한다")
    void createBettingMoney() {
        BettingMoney bettingMoney = new BettingMoney(10000);

        assertThat(bettingMoney.getValue()).isEqualTo(10000);
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 100, 1100, 1001})
    @DisplayName("베팅 금액은 1000 단위가 아니면 예외가 발생한다")
    void validateUnitTest(int value) {
        assertThatThrownBy(() -> new BettingMoney(value))
                .isInstanceOf(InvalidMoneyValueException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {10_001_000, 100_000_000})
    @DisplayName("베팅 금액은 10,000,000 이하가 아니면 예외가 발생한다")
    void validateBoundTest(int value) {
        assertThatThrownBy(() -> new BettingMoney(value))
                .isInstanceOf(InvalidMoneyValueException.class);
    }
}
