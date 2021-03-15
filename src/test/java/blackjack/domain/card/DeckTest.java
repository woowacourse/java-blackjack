package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class DeckTest {

    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck(Card.values());
    }

    @Test
    @DisplayName("카드 드로우 성공")
    void drawCardDeck() {
        assertThat(deck.drawCard()).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("남아 있는 카드가 없어 드로우 실패")
    void noRemainCard() {
        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }
        assertThatThrownBy(() -> deck.drawCard()).isInstanceOf(IndexOutOfBoundsException.class);
    }
}
