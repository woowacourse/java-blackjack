package blackjack.domain.betting;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class BettingMoneyTest {

    @ParameterizedTest
    @DisplayName("배팅 금액은 숫자가 아닌 다른 값이 들어오면 에러가 발생한다.")
    @NullSource
    @EmptySource
    @ValueSource(strings = {"a", "ㄱ", "!", "0", "-1000"})
    void createBettingMoneyTest(String money) {
        assertThatThrownBy(() -> new BettingMoney(money))
            .isInstanceOf(IllegalArgumentException.class);
    }
}