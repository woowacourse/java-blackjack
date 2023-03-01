package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    private Participants participants;

    @BeforeEach
    void setUp() {
        participants = Participants.from(List.of("leo", "reo", "reoleo"));
    }

    @Test
    void participantsTest() {
        assertThatCode(() -> Participants.from(List.of("leo", "reo", "reoleo"))).doesNotThrowAnyException();
    }

    @Test
    void participantsDealTest() {
        participants.deal(Deck.from(new RandomShuffleStrategy()));
        List<Participant> participantsList = participants.getParticipants();
        for (Participant participant : participantsList) {
            Assertions.assertThat(participant.getCardNames().size()).isEqualTo(1);
        }
    }

    @Test
    void getPlayersTest() {
        assertThat(participants.getPlayers().size()).isEqualTo(3);
    }
}
