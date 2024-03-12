package blackjack.domain.bet;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.player.bet.BetAmount;
import blackjack.exception.NeedRetryException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetAmountTest {

    @DisplayName("배팅금액은 음수일 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, -10, -999})
    void validateNegative(int betAmount) {
        assertThatThrownBy(() -> new BetAmount(betAmount))
                .isInstanceOf(NeedRetryException.class)
                .hasMessage("배팅 금액은 0이상만 가능합니다.");
    }

    @DisplayName("배팅금액은 0이상이다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 10, 1000, 999999})
    void validatePositiveOrZero(int amount) {
        assertThatCode(() -> new BetAmount(amount))
                .doesNotThrowAnyException();
    }
}
