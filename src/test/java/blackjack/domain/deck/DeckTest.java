package blackjack.domain.deck;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    void 카드_뽑기_테스트() {
        // when
        Card card = Deck.pop();

        // then
        assertThat(card).isInstanceOf(Card.class);
    }

}
