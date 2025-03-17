package view;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ValidatorTest {
    @DisplayName("등록한 유저가 기준보다 적거나 많으면 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("userExceptionTestCase")
    void test1(List<String> players) {
        assertThatThrownBy(() -> Validator.validateNames(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유저는 1명 이상 7명 이하로 등록해야 합니다.");
    }

    private static Stream<Arguments> userExceptionTestCase() {
        return Stream.of(
                Arguments.arguments(List.of("수양", "레몬", "키키", "나나", "모모", "부부", "롸롸", "꾸꾸")
                )
        );
    }

    @DisplayName("유저는 중복될 수 없다.")
    @ParameterizedTest
    @MethodSource("userExceptionTestCase2")
    void test2(List<String> players) {
        assertThatThrownBy(() -> Validator.validateNames(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유저는 중복될 수 없습니다.");
    }

    private static Stream<Arguments> userExceptionTestCase2() {
        return Stream.of(
                Arguments.arguments(List.of("수양", "레몬", "레몬", "레몬", "모모", "롸롸", "꾸꾸")
                )
        );
    }


}
