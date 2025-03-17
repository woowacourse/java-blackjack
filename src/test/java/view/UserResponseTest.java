package view;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserResponseTest {

    @ParameterizedTest
    @DisplayName("잘못된 유저 응답이면 예외를 반환합니다.")
    @ValueSource(strings = {"N", "Y", "yes", "no", "", " "})
    void validateUserResponseErrorTest(String value) {
        Assertions.assertThatThrownBy(() -> UserResponse.getUserResponse(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("응답은 반드시 y 혹은 n만 가능합니다.");

    }

    @ParameterizedTest
    @DisplayName("올바른 유저 응답이면 예외를 반환하지 않습니다.")
    @ValueSource(strings = {"n", "y"})
    void validateUserResponseTest(String value) {
        Assertions.assertThatCode(() -> UserResponse.getUserResponse(value)).doesNotThrowAnyException();
    }
}
