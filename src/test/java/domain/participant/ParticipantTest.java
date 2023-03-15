package domain.participant;

import static domain.Fixtures.ACE_CLOVER;
import static domain.Fixtures.FOUR_HEART;
import static domain.Fixtures.TWO_SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Shape;
import domain.card.Value;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    private static final Card NINE_SPADE = new Card(Value.NINE, Shape.SPADE);
    private Participant mango;

    @BeforeEach
    void setUp() {
        Cards initialCards = new Cards(List.of(TWO_SPADE, FOUR_HEART));
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
        mango.receiveCard(NINE_SPADE);
        mango.receiveCard(ACE_CLOVER);
        assertThat(mango.getScore()).isEqualTo(16);
    }

    @DisplayName("Ace 카드가 있는 경우, 상황에 따라 11로 계산할 수 있다.")
    @Test
    void aceScoreElevenTest() {
        mango.receiveCard(ACE_CLOVER);
        assertThat(mango.getScore()).isEqualTo(17);
    }
}
