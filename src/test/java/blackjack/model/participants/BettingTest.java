package blackjack.model.participants;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingTest {

    @DisplayName("돈의 금액이 최소 금액보다 작으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-200000, -2, -1})
    void validateMoney(int given) {
        assertThatThrownBy(() -> new Betting(given))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
