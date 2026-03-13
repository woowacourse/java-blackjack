package domain.vo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {
    static Stream<Arguments> provideInputAndResult() {
        return Stream.of(
                Arguments.of("10000", 10000),
                Arguments.of("150000", 150000),
                Arguments.of("6000000", 6000000)
        );
    }
    @ParameterizedTest
    @MethodSource("provideInputAndResult")
    void 정상_생성_테스트(String input, int value) {
        Money result = new Money(input);

        assertThat(result).isEqualTo(value);
    }

    @ParameterizedTest
    @ValueSource(strings = {"포비", "10원", "a1017", "aa"})
    void 정수가_아닐_때_예외_테스트(String input) {
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("정수를 입력해야");
    }

    @ParameterizedTest
    @ValueSource(strings = {"9999", "10001", "0", "-10000", "11000"})
    void 최소_금액과_단위_미충족_테스트(String input) {
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("단위여야 하며, 최소 금액은");
    }
}