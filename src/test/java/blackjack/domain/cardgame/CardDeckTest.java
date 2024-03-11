package blackjack.domain.cardgame;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardDeckTest {

    @Test
    void 덱에서_카드를_뽑을_수_있다() {
        CardDeck deck = CardDeck.create();

        Card card = deck.draw();

        assertThat(card).isNotNull();
    }
}
