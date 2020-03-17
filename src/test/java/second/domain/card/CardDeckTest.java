package second.domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardDeckTest {
    @Test
    void 카드덱_생성() {
        CardDeck cardDeck = new CardDeck();

        assertThat(cardDeck).isInstanceOf(CardDeck.class);
    }

    @Test
    void drawCard() {
        CardDeck cardDeck = new CardDeck();

        assertThat(cardDeck.pickCard()).isInstanceOf(Card.class);
    }

    @Test
    void 카드_수를_넘어가는_뽑기() {
        CardDeck cardDeck = new CardDeck();
        for (int i = 0; i < 48; i++) {
            cardDeck.pickCard();
        }

        assertThatThrownBy(() -> cardDeck.pickCard()).isInstanceOf(IllegalStateException.class);
    }
}