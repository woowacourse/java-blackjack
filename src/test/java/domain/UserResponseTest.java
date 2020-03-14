package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserResponseTest {
    @Test
    @DisplayName("enum을 입력값을 통해 반환")
    void valueOf() {
        assertThat(UserResponse.of("y")).isEqualTo(UserResponse.YES);
        assertThat(UserResponse.of("n")).isEqualTo(UserResponse.NO);
    }

    @Test
    @DisplayName("입력값이 y인지 확인")
    void isYes() {
        UserResponse userResponse = UserResponse.of("y");
        assertThat(userResponse.isYes()).isTrue();
    }
}
