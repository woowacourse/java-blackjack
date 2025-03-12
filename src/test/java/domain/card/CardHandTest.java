package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
class CardHandTest {

    private final CardHand cardHand = new CardHand();

    @Test
    void 손패에_카드를_추가할_수_있다() {
        cardHand.takeCards(Card.DIAMOND_2);

        List<Card> cards = cardHand.getCards();
        assertThat(cards).contains(Card.DIAMOND_2);
    }

    @Test
    void 카드의_점수의_합을_구할수있다() {
        cardHand.takeCards(Card.CLOVER_J, Card.HEART_5);

        var score = cardHand.calculateScore();

        assertThat(score).isEqualTo(15);
    }

    @Test
    void A가있을때_나머지숫자의_합이_11이상이면_1을_선택한다() {
        cardHand.takeCards(Card.HEART_A, Card.CLOVER_A);

        var score = cardHand.calculateScore();

        assertThat(score).isEqualTo(12);
    }

    @Test
    void A가있을때_나머지숫자의_합이_10이하이면_11을_선택한다() {
        cardHand.takeCards(Card.HEART_A, Card.CLOVER_J);

        var score = cardHand.calculateScore();

        assertThat(score).isEqualTo(21);
    }
}
