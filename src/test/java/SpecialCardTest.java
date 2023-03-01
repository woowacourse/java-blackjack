import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class SpecialCardTest {

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "M", "B", "A1"})
    @DisplayName("A, J, Q, K 이외의 값이 들어오면 실패한다.")
    void shouldFailDoesNotSpecial(String value) {
        assertThat(SpecialCard.isSpecial(value)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "J", "Q", "K"})
    @DisplayName("A, J, Q, K가 들어오면 성공한다.")
    void shouldSuccessSpecial(String value) {
        assertThat(SpecialCard.isSpecial(value)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "M", "B", "A1"})
    @DisplayName("특수 카드 값이 아니면 예외 처리한다.")
    void shouldThrowDoesNotSpecial(String value) {
        assertThatThrownBy(() -> SpecialCard.convertNumber(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("특수 카드가 아닙니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"A, 1", "J, 10", "Q, 10", "K, 10"})
    @DisplayName("특수 카드면 해당하는 숫자로 변환한다.")
    void shouldSuccessConvertIsSpecial(String value, int number) {
        assertThat(SpecialCard.convertNumber(value)).isEqualTo(number);
    }
}
