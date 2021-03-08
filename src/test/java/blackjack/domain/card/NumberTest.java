package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NumberTest {
    private static Stream<Arguments> numberWithString() {
        return Stream.of(
                Arguments.of("A", Number.ACE),
                Arguments.of("2", Number.TWO),
                Arguments.of("3", Number.THREE),
                Arguments.of("4", Number.FOUR),
                Arguments.of("5", Number.FIVE),
                Arguments.of("6", Number.SIX),
                Arguments.of("7", Number.SEVEN),
                Arguments.of("8", Number.EIGHT),
                Arguments.of("9", Number.NINE),
                Arguments.of("10", Number.TEN),
                Arguments.of("J", Number.JACK),
                Arguments.of("Q", Number.QUEEN),
                Arguments.of("K", Number.KING)
        );
    }

    @DisplayName("숫자카드 문자(A, 1,2,3 ... J, Q, K)을 통해 Number 가져온다.")
    @ParameterizedTest
    @MethodSource("numberWithString")
    void number_from_test(String name, Number number) {
        Number findNumber = Number.from(name);

        assertThat(findNumber).isEqualTo(number);
    }

    @DisplayName("숫자카드 문자(A, 1,2,3 ... J, Q, K)가 아닌 문자가 들어오면 에러가 발생한다. ")
    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "B", "a"})
    void number_wrong_name_exception_test(String name) {
        assertThatThrownBy(() -> Number.from(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("입력된 숫자는 없는 카드 숫자입니다! : %s", name));
    }

    @DisplayName("입력한 문자로 가져온 Number 객체의 name은 입력값과 같아야 한다.")
    @Test
    void number_get_test() {
        //given
        String numberName = "A";

        //when
        Number findNumber = Number.from(numberName);

        //then
        assertThat(findNumber.getName()).isEqualTo(numberName);
    }
}
