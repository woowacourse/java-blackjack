package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CardDeckTest {

    @Test
    void 카드_덱을_초기화한다() {
        // when
        CardDeck cardDeck = CardDeck.of();

        // then
        assertThat(cardDeck.getCards())
                .hasSize(52);
    }

    @Test
    void 가장_위에_있는_카드를_반환한다() {
        // given
        CardDeck deck = CardDeck.of();
        Card last = deck.getCards().getLast();

        // when
        Card card = deck.popCard();

        // then
        assertThat(card).isEqualTo(last);
    }
}
