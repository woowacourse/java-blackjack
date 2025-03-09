package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
class CardDeckTest {

    private final CardDeck cardDeck = new CardDeck();

    @Test
    void 카드덱에_카드를_추가할_수_있다() {
        cardDeck.takeCards(Card.DIAMOND_2);

        List<Card> cards = cardDeck.getCards();
        assertThat(cards).contains(Card.DIAMOND_2);
    }

    @Test
    void 카드의_점수의_합을_구할수있다() {
        cardDeck.takeCards(Card.CLOVER_J, Card.HEART_5);

        var score = cardDeck.calculateScore();

        assertThat(score).isEqualTo(15);
    }

    @Test
    void A가있을때_나머지숫자의_합이_11이상이면_1을_선택() {
        cardDeck.takeCards(Card.HEART_A, Card.CLOVER_A);

        var score = cardDeck.calculateScore();

        assertThat(score).isEqualTo(12);
    }

    @Test
    void A가있을때_나머지숫자의_합이_10이하이면_11을_선택() {
        cardDeck.takeCards(Card.HEART_A, Card.CLOVER_J);

        var score = cardDeck.calculateScore();

        assertThat(score).isEqualTo(21);
    }
}
