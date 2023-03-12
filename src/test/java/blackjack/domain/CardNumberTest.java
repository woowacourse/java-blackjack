package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CardNumberTest {
    @DisplayName("카드 숫자를 입력하면 해당하는 점수를 받을 수 있다.")
    @ParameterizedTest(name = "{displayName} [{index}] => ''{0}'' is ''{1}''")
    @CsvSource(value = {"A:11", "2:2", "J:10"}, delimiter = ':')
    void Should_GetScore_When_InputNumber(String number, int score) {
        assertThat(CardNumber.scoreByNumber(number)).isEqualTo(score);
    }

    @DisplayName("카드 숫자가 아닌 경우 예외가 발생한다.")
    @ParameterizedTest(name = "{displayName} [{index}] => ''{0}''")
    @ValueSource(strings = {"N", "11", "a"})
    void Should_ThrowException_When_InputNumber(String number) {
        assertThatThrownBy(() -> CardNumber.scoreByNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자를 찾을 수 없습니다.");
    }
}
