package blackjack.domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
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
                .hasMessage("베팅 금액은 숫자만 가능합니다.");
    }

    @DisplayName("Money는 1,000~1,000,000사이의 숫자가 들어왔을 때 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"1000", "12345", "314734", "1000000"})
    void createMoneyWhenGivenNumber(String input) {
        // when & then
        assertDoesNotThrow(() -> new Money(input));
    }

    @DisplayName("Money는 1,000~1,000,000사이의 숫자가 아니면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"999", "1000001"})
    void throwsExceptionWhenInvalidRange(String input) {
        // when & then
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 1000~1000000사이의 숫자만 가능합니다.");
    }
}
