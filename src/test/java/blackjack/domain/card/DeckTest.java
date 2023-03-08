package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck();
    }

    @Test
    @DisplayName("Deck 생성 시 52장의 카드가 존재한다.")
    void create() {
        for (Number number : Number.values()) {
            for (Suit suit : Suit.values()) {
                Card card = new Card(number, suit);
                assertThat(deck.containsCard(card)).isTrue();
            }
        }
    }

    @Test
    @DisplayName("무작위의 카드를 한 장 뽑은 후, deck에 존재하는지 확인한다.")
    void drawACard() {
        Card selectedCard = deck.drawCard();

        assertThat(deck.containsCard(selectedCard)).isFalse();
    }
}
