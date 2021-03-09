package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeckTest {

    @Test
    @DisplayName("deck 생성")
    void testDeckInit() {
        Deck deck = new Deck();
        assertThat(deck).isNotNull();
    }

    @Test
    void testDrawCard() {
        Deck deck = new Deck();

        Card card = deck.draw();
        assertThat(card).isNotNull();
    }

    @Test
    void test() {
        Deck deck = new Deck();

        for (int i = 0; i < 52; i++) {
            deck.draw();
        }

        assertThatThrownBy(deck::draw).isInstanceOf(IndexOutOfBoundsException.class);
    }
}
