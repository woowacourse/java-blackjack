package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantTest {

    @Test
    @DisplayName("Player 클래스는 이름을 입력받으면 정상적으로 생성된다.")
    void create_player() {
        Name name = new Name("aki");

        assertThatCode(() -> new Participant(name)).doesNotThrowAnyException();
    }
}
