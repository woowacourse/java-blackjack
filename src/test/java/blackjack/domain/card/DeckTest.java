package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    @Test
    @DisplayName("두장의 카드를 나눠주고 나눠준 카드는 카드 캐쉬에서 지운다.")
    void drawTwoCardsTest() {
        Deck deck = new Deck();
        Cards cards = deck.drawInitCards();
        int expectedDrawnCards = 2;
        int expectedCardSize = 50;
        assertThat(cards.getCards().size()).isEqualTo(expectedDrawnCards);
        assertThat(deck.size()).isEqualTo(expectedCardSize);
    }

    @Test
    @DisplayName("한장의 카드를 나눠주고 나눠준 카드는 카드 캐쉬에서 지운다.")
    void drawOneCard() {
        Deck deck = new Deck();
        deck.drawOneCard();
        int expectedCardSize = 51;
        assertThat(deck.size()).isEqualTo(expectedCardSize);
    }
}
