package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ParticipantsTest {
    @Test
    void participantsTest() {
        assertThatCode(() -> Participants.from(List.of("leo", "reo", "reoleo")))
            .doesNotThrowAnyException();
    }

    @Test
    void participantsDealTest() {
        Participants participants = Participants.from(List.of("leo", "reo", "reoleo"));
        participants.deal(Deck.from(new RandomShuffleStrategy()));
        List<Participant> participantsList = participants.getParticipants();
        for (Participant participant : participantsList) {
            Assertions.assertThat(participant.getCardNames().size()).isEqualTo(1);
        }
    }
}
