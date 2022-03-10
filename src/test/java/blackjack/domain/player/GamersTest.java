package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GamersTest {

    @Test
    @DisplayName("중복된 게이머 이름으로 에러가 발생한다.")
    void duplicateGamerNameException() {
        assertThatThrownBy(() -> new Gamers(List.of(new Gamer("huni"), new Gamer("huni"))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 중복된 이름은 입력할 수 없습니다.");
    }
}
