package domain.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class NameTest {
    private static Stream<Arguments> invalidNamesAndReason() {
        return Stream.of(
                Arguments.of("", "공백인 이름이 존재합니다.", "이름이 입력되지 않은 경우"),
                Arguments.of(" ", "공백인 이름이 존재합니다.", "공백인 이름이 존재하는 경우"),
                Arguments.of("aaaaaa", "이름은 최대 5자입니다: aaaaaa", "이름이 5자를 넘는 경우")
        );
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("invalidNamesAndReason")
    @DisplayName("이름에 대한 예외")
    void invalidNameTest(String name, String errorMessage, String reason) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);
    }
}
