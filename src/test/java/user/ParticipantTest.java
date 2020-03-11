package user;

import card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParticipantTest {
    @DisplayName("생성했을 때, 두 장의 카드를 보유하고 있는지 확인")
    @Test
    void constructor_InitializeParticipant_HandsSizeIsTwo() {
        ParticipantName name = new ParticipantName("또링");
        Deck deck = new Deck();
        Participant participant = new Participant(name, deck);
        assertThat(participant.handSize()).isEqualTo(2);
    }
}
