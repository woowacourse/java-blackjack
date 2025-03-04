package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CardDeckTest {

    @Test
    void 카드_덱을_초기화한다() {
        CardDeck cardDeck = new CardDeck();

        assertThat(cardDeck.getCardDeck())
                .hasSize(52);
    }

}
