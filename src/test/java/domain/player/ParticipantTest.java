package domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParticipantTest {

    @Test
    @DisplayName("참가자의 이름이 딜러면 예외가 발생한다")
    void givenDealerName_theFailed() {
        assertThatThrownBy(() -> Participant.from("딜러"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자의 이름은 딜러가 될 수 없습니다.");
    }
}
