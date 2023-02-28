package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardDeckTest {

    @Test
    @DisplayName("카드 52개 생성한다")
    void createCardDeckTest() {
        CardDeck cardDeck = CardDeck.generate();

        assertThat(cardDeck.getCards()).hasSize(52);
    }

    @Test
    @DisplayName("카드 한 장을 나눠준다")
    void pickCard() {
        CardDeck cardDeck = CardDeck.generate();

        Card card = cardDeck.pick();

        assertThat(CardSuit.values()).contains(card.getSuit());
    }
}
