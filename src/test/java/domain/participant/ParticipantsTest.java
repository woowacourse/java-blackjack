package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Test
    @DisplayName("첫 턴에서 모든 참가자가 카드를 두개씩 뽑는지 확인")
    void initialTurnTest(){
        Dealer dealer = new Dealer();
        Players players = Players.of("runa, kun");
        Participants participants = new Participants(dealer,players);

        participants.initialTurn();
        int actual = (int)participants.getParticipants().stream()
            .filter(participant -> participant.getCards().size() == 2)
            .count();

        assertThat(actual).isEqualTo(3);
    }
}