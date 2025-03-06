package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Test
    @DisplayName("모든 참가자 중 플레이어만 가져온다")
    void getPlayerParticipants() {
        // given
        List<Participant> participantsSource = List.of(
                new Dealer(),
                new Player("a"),
                new Player("b")
        );
        Participants participants = new Participants(participantsSource);

        // when
        List<Participant> players = participants.getPlayerParticipants();

        // then
        assertThat(players).hasSize(2);
    }
}
