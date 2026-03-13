package blackjack.model.bet;

import static org.junit.jupiter.api.Assertions.*;

import blackjack.model.user.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetAmountTest {

    @ParameterizedTest
    @ValueSource(strings = {"1", "10"})
    @DisplayName("배팅 금액 정상 생성")
    void createBetAmount_success(String input) {
        // given
        Player player = new Player("name");

        //when & then
        assertDoesNotThrow(() -> new BetAmount(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("입력값이 공백일 경우 예외 발생")
    void createBetAmount_fail_when_input_is_empty(String emptyInput) {
        // given
        Player player = new Player("name");

        //when & then
        Assertions.assertThatThrownBy(() -> new BetAmount(emptyInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "1a", "123*"})
    @DisplayName("입력값이 숫자 형태가 아닐 경우 예외 발생")
    void createBetAmount_fail_when_input_is_not_number(String stringInput) {
        // given
        Player player = new Player("name");

        //when & then
        Assertions.assertThatThrownBy(() -> new BetAmount(stringInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1"})
    @DisplayName("입력값이 양수가 아닐 경우 예외 발생")
    void createBetAmount_fail_when_input_is_not_positive(String negativeInput) {
        // given
        Player player = new Player("name");

        //when & then
        Assertions.assertThatThrownBy(() -> new BetAmount(negativeInput))
                .isInstanceOf(IllegalArgumentException.class);
    }
}