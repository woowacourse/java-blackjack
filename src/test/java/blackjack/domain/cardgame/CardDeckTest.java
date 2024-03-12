package blackjack.domain.cardgame;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardDeckTest {
    @Test
    void 덱에서_카드를_뽑을_수_있다() {
        // given
        CardDeck deck = new CardDeck();

        // when
        Card card = deck.draw();

        // then
        assertThat(card).isNotNull();
    }
}
