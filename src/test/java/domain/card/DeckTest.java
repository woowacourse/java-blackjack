package domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {

    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck() {
            @Override
            public Card draw() {
                return new Card(1, Shape.SPADE);
            }
        };
    }

    @Test
    @DisplayName("덱에서 카드 하나를 뽑는다.")
    void draw_ByIndex() {
        assertThat(deck.draw().toString()).isEqualTo("A스페이드");
    }

    @Test
    @DisplayName("처음 두장을 뽑는다.")
    void drawInitialHands() {
        assertThat(deck.drawInitialHands()).hasSize(2);
    }
}
