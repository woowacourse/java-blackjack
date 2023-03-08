package domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantsTest {

    @Test
    @DisplayName("플레이어 이름 리스트를 반환한다.")
    void drawCardTest() {
        //given
        final Participants participants = Participants.from(List.of(
                Participant.from("파워", BetAmount.from("10000")),
                Participant.from("준팍", BetAmount.from("10000")),
                Participant.from("서브웨이", BetAmount.from("10000"))
        ));

        //then
        assertThat(participants.getNames())
                .isEqualTo(List.of("파워", "준팍", "서브웨이"));
    }
}
