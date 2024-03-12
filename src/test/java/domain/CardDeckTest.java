package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardDeckTest {

    @DisplayName("카드 덱에서 한 장 뽑는다.")
    @Test
    void draw() {
        CardDeck cardDeck = new CardDeck();

        assertThat(cardDeck.draw())
                .isInstanceOf(Card.class);
    }
}
