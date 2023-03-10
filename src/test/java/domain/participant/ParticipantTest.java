package domain.participant;

import domain.card.Card;
import domain.card.Shape;
import domain.card.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantTest {

    private Participant mango;

    @BeforeEach
    void setUp() {
        List<Card> initialCards = List.of(new Card(Value.TWO, Shape.SPADE), new Card(Value.FOUR, Shape.HEART));
        mango = new Participant(new Name("망고")) {
            @Override
            boolean isHittable() {
                return true;
            }
        };
        mango.receiveInitialCards(initialCards);
    }

    @DisplayName("Ace 카드가 없는 경우, 카드의 숫자를 더해 계산할 수 있다.")
    @Test
    void scoreTest() {
        assertThat(mango.getScore()).isEqualTo(6);
    }

    @DisplayName("Ace 카드가 있는 경우, 상황에 따라 1로 계산할 수 있다.")
    @Test
    void aceScoreOneTest() {
        mango.receiveCard(new Card(Value.NINE, Shape.SPADE));
        mango.receiveCard(new Card(Value.ACE, Shape.CLOVER));
        assertThat(mango.getScore()).isEqualTo(16);
    }

    @DisplayName("Ace 카드가 있는 경우, 상황에 따라 11로 계산할 수 있다.")
    @Test
    void aceScoreElevenTest() {
        mango.receiveCard(new Card(Value.ACE, Shape.HEART));
        assertThat(mango.getScore()).isEqualTo(17);
    }
}
