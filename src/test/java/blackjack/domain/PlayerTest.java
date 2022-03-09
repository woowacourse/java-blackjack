package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("Player 클래스는 이름, 카드들을 입력받으면 정상적으로 생성된다.")
    void create_player() {
        Name name = new Name("aki");

        assertThatCode(() -> new Player(name)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Player 클래스는 이름을 입력받으면 새 플레이어를 정상적으로 생성된다.")
    void create_new_player() {
        Name name = new Name("aki");
        assertThatCode(() -> Player.from(name)).doesNotThrowAnyException();
    }
}
