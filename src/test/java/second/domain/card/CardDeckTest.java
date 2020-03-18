package second.domain.card;

import org.junit.jupiter.api.Test;
import second.domain.card.provider.CardDeck;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardDeckTest {
    @Test
    void 카드덱_생성() {
        CardDeck CardDeck = new CardDeck();

        assertThat(CardDeck).isInstanceOf(CardDeck.class);
    }

    @Test
    void drawCard() {
        CardDeck CardDeck = new CardDeck();

        assertThat(CardDeck.pickCard()).isInstanceOf(Card.class);
    }

    @Test
    void 카드_수를_넘어가는_뽑기() {
        CardDeck CardDeck = new CardDeck();
        for (int i = 0; i < 48; i++) {
            CardDeck.pickCard();
        }

        assertThatThrownBy(() -> CardDeck.pickCard()).isInstanceOf(IllegalStateException.class);
    }
}