package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Shape;
import domain.card.Value;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    private Participant participant;

    @BeforeEach
    void setUp() {
        List<Card> initialCards = new ArrayList<>(List.of(new Card(Value.TWO, Shape.SPADE), new Card(Value.FOUR, Shape.HEART)));
        Name mango = new Name("망고");
        participant = new Participant(mango, initialCards);
    }

    @DisplayName("참가자 이름 확인을 통해 딜러 생성에 성공하였는지 알 수 있다.")
    @Test
    void createTest() {
        assertThat(participant.getName()).isEqualTo("망고");
    }

    @DisplayName("Ace 카드가 없는 경우, 카드의 숫자를 더해 계산할 수 있다.")
    @Test
    void scoreTest(){
        assertThat(participant.calculateScore()).isEqualTo(6);
    }

    @DisplayName("Ace 카드가 있는 경우, 상황에 따라 1로 계산할 수 있다.")
    @Test
    void aceScoreOneTest() {
        participant.receiveCard(new Card(Value.NINE, Shape.SPADE));
        participant.receiveCard(new Card(Value.ACE, Shape.CLOVER));
        assertThat(participant.calculateScore()).isEqualTo(16);
    }

    @DisplayName("Ace 카드가 있는 경우, 상황에 따라 11로 계산할 수 있다.")
    @Test
    void aceScoreElevenTest(){
        participant.receiveCard(new Card(Value.ACE, Shape.HEART));
        assertThat(participant.calculateScore()).isEqualTo(17);
    }
}
