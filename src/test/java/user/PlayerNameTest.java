package user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerNameTest {

    @DisplayName("이름이 null일 경우 오류를 던진다.")
    @Test
    void nullInput() {
        Assertions.assertThatThrownBy(() -> new PlayerName(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이름을 입력해 주세요");
    }

    @DisplayName("이름이 빈칸으로 이루어진 경우 오류를 던진다.")
    @Test
    void blankInput() {
        Assertions.assertThatThrownBy(() -> new PlayerName("   "))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이름을 입력해 주세요");
    }
}
