package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
class CardDeckTest {

    private final CardDeck cardDeck = new CardDeck();
    private final Card jClover = new Card(Rank.JACK, Shape.CLOVER);
    private final Card fiveHeart = new Card(Rank.FIVE, Shape.HEART);
    private final Card aceHeart = new Card(Rank.ACE, Shape.HEART);
    private final Card aceClover = new Card(Rank.ACE, Shape.CLOVER);
    private final Card twoDiamond = new Card(Rank.TWO, Shape.DIAMOND);

    @Test
    void 카드덱에_카드를_추가할_수_있다() {
        cardDeck.takeCards(twoDiamond);

        List<Card> cards = cardDeck.getCards();
        assertThat(cards).contains(twoDiamond);
    }

    @Test
    void 카드의_점수의_합을_구할수있다() {
        cardDeck.takeCards(jClover, fiveHeart);

        var score = cardDeck.calculateScore();

        assertThat(score).isEqualTo(15);
    }

    @Test
    void A가있을때_나머지숫자의_합이_11이상이면_1을_선택() {
        cardDeck.takeCards(aceHeart, aceClover);

        var score = cardDeck.calculateScore();

        assertThat(score).isEqualTo(12);
    }

    @Test
    void A가있을때_나머지숫자의_합이_10이하이면_11을_선택() {
        cardDeck.takeCards(aceHeart, jClover);

        var score = cardDeck.calculateScore();

        assertThat(score).isEqualTo(21);
    }
}
