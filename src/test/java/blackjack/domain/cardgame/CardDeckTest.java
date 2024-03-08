package blackjack.domain.cardgame;

import blackjack.domain.card.Card;
import blackjack.domain.cardgame.CardDeck;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardDeckTest {

    @Test
    void 덱에서_카드를_뽑을_수_있다() {
        CardDeck deck = new CardDeck();

        Card card = deck.draw();

        assertThat(card).isNotNull();
    }
}
