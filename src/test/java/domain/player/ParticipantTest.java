package domain.player;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {
    @Test
    @DisplayName("정해진 이름이 없다면 예외가 발생한다")
    void nameNotDetermined() {
        final Participant participant = new Dealer();

        assertThatThrownBy(participant::getName)
                .isInstanceOf(IllegalCallerException.class)
                .hasMessage("참여자의 이름이 정해지지 않았습니다");
    }
}
