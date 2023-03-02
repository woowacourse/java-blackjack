package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.participant.Name;
import domain.participant.Participant;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    private Participant participant;

    @BeforeEach
    void setUp() {
        List<Card> initialCards = new ArrayList<>(List.of(new Card("2", "스페이드"), new Card("4", "하트")));
        Name mango = new Name("망고");
        participant = new Participant(mango, initialCards);
    }

    @DisplayName("Ace 카드가 없는 경우, 카드의 숫자를 더해 계산할 수 있다.")
    @Test
    void scoreTest(){
        assertThat(participant.calculateScore()).isEqualTo(6);
    }

    @DisplayName("Ace 카드가 있는 경우, 상황에 따라 1로 계산할 수 있다.")
    @Test
    void aceScoreOneTest() {
        participant.receiveCard(new Card("9", "스페이드"));
        participant.receiveCard(new Card("A", "클로버"));
        assertThat(participant.calculateScore()).isEqualTo(16);
    }

    @DisplayName("Ace 카드가 있는 경우, 상황에 따라 11로 계산할 수 있다.")
    @Test
    void aceScoreElevenTest(){
        participant.receiveCard(new Card("A", "하트"));
        assertThat(participant.calculateScore()).isEqualTo(17);
    }
}
