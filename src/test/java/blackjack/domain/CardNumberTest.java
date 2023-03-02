package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CardNumberTest {

    @DisplayName("카드 숫자를 입력하면 가능한 점수 리스트를 받을 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"A:1,11", "2:2", "J:10"}, delimiter = ':')
    void Should_GetScore_When_InputNumber(String number, String score) {
        List<Integer> expected = Stream.of(score.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> result = CardNumber.getScoresByNumber(number);

        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("카드 숫자가 아닌 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"N", "11", "a"})
    void Should_ThrowException_When_InputNumber(String number) {
        assertThatThrownBy(() -> CardNumber.getScoresByNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자를 찾을 수 없습니다.");
    }
}
