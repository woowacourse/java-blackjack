package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ParticipantTest {
    @Test
    void test() {
        List<Card> cards = new ArrayList<>(List.of(new Card("3", "스페이드"), new Card("10", "하트")));
        Participant participant = new Participant(new Name("mango"), cards);
        assertThat(participant.calculateScore()).isEqualTo(13);
        participant.receiveCard(new Card("A", "클로버"));
        assertThat(participant.calculateScore()).isEqualTo(14);
    }

}
