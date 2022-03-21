package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @DisplayName("CardDeck 인스턴스가 생성되는지 확인한다.")
    @Test
    void createDeck() {
        CardDeck cardDeck = new CardDeck();

        assertThat(cardDeck).isInstanceOf(CardDeck.class);
    }
}