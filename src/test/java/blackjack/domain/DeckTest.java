package blackjack.domain;

import static blackjack.domain.Number.ACE;
import static blackjack.domain.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Stack;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DeckTest {

    @Test
    void 카드를_뽑는다() {
        final Stack<Card> cards = new Stack<>();
        final Card card = new Card(ACE, SPADE);
        cards.add(card);

        final Deck deck = new Deck(cards);

        assertThat(deck.draw()).isEqualTo(card);
    }
}

class Deck {

    private final Stack<Card> cards;

    public Deck(final Stack<Card> cards) {
        this.cards = cards;
    }

    public Card draw() {
        return cards.pop();
    }
}
