package domain.card;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CardDeckTest {

    @Test
    void 카드덱에서_카드를_한장_뽑을_수_있다() {
        CardDeck deck = new CardDeck(new NoShuffleStrategy());

        Card card = deck.draw();

        assertNotNull(card);
    }

    @Test
    void 카드덱은_52장의_중복되지않은_카드를_생성한다() {
        CardDeck deck = new CardDeck(new NoShuffleStrategy());

        Set<Card> cards = new HashSet<>();

        for (int i = 0; i < 52; i++) {
            cards.add(deck.draw());
        }

        assertEquals(52, cards.size());
    }
}
