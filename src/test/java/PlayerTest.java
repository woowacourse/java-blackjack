import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.*;

public class PlayerTest {

    @Test
    @DisplayName("이름을 받아 플레이어를 생성한다")
    void createPlayerTest() {
        Player player = new Player("boxster");

        assertThat(player.getName()).isEqualTo("boxster");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("이름에 공백 혹은 빈값만 들어온다면 예외를 발생시킨다")
    void createException(String name) {
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 공백일 수 없습니다.");
    }
}
