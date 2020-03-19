package domain.card.providable;

import domain.card.Card;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CardDeckTest {
    @Test
    void 카드뽑기테스트() {
        CardDeck cardDeck = new CardDeck();
        assertThat(cardDeck.giveCard()).isInstanceOf(Card.class);
    }

    @Test
    void 빈덱에카드뽑기테스트() {
        CardDeck cardDeck = new CardDeck();
        for (int i = 0; i < 48; i++) {
            cardDeck.giveCard();
        }

        assertThatThrownBy(() -> cardDeck.giveCard()).isInstanceOf(IllegalStateException.class);
    }
}
