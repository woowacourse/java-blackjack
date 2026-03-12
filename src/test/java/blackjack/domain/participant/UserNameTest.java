package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserNameTest {

    @Test
    @DisplayName("유저 이름이 공백일 경우 예외 발생")
    void validate_empty() {
        // given
        String name = " ";

        // when & then
        assertThatThrownBy(() -> new UserName(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("공백");
    }

    @Test
    @DisplayName("유저 이름이 5자가 넘을 경우 예외 발생")
    void validate_length() {
        // given
        String name = "흑곰흑곰흑곰";

        // when & then
        assertThatThrownBy(() -> new UserName(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("5자");
    }
}
