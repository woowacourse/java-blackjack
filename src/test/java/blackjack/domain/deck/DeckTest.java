package blackjack.domain.deck;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    void 카드_꺼내오기_테스트() {
        // given
        Deck deck = Deck.createWithShuffled(new CollectionsShuffle());

        // when
        Card card = deck.pop();

        // then
        assertThat(card).isInstanceOf(Card.class);
    }

}
