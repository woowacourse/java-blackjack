package first.domain;

import first.domain.card.Card;
import first.domain.card.providable.CardDeck;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CardDeckTest {

    @Test
    void 빈덱에카드뽑기테스트() {
        CardDeck cardDeck = new CardDeck();
        for (int i = 0; i < 48; i++) {
            cardDeck.giveCard();
        }

        assertThatThrownBy(() -> cardDeck.giveCard()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 카드뽑기테스트() {
        CardDeck cardDeck = new CardDeck();
        assertThat(cardDeck.giveCard()).isInstanceOf(Card.class);
    }
}