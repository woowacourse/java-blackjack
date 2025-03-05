package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.participant.Player;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @DisplayName("이름으로 Player 객체를 생성한다.")
    @Test
    void createAttendee() {
        // given
        String nickname = "pobi";

        // when & then
        assertThatCode(() -> new Player(nickname, new ArrayList<>()))
                .doesNotThrowAnyException();
    }


}
