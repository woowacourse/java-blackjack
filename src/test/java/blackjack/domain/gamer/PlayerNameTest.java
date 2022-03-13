package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.gamer.PlayerName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerNameTest {

    @Test
    @DisplayName("Name 클래스는 이름을 입력받으면 정상적으로 생성된다.")
    void create_name() {
        assertThatCode(() -> new PlayerName("aki")).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("이름으로 공백을 입력받으면 에러를 출력한다.")
    void empty_name(String input) {
        assertThatThrownBy(() -> new PlayerName(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
