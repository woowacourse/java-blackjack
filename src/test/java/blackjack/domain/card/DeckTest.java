package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeckTest {

    @Test
    @DisplayName("deck 생성")
    void testDeckInit() {
        //when
        Deck deck = new Deck();

        //then
        assertThat(deck).isNotNull();
    }

    @Test
    void testDrawCard() {
        //given
        Deck deck = new Deck();

        //when
        Card card = deck.draw();

        //then
        assertThat(card).isNotNull();
    }

    @Test
    void test() {
        //given
        Deck deck = new Deck();

        //when
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }

        //then
        assertThatThrownBy(deck::draw).isInstanceOf(IndexOutOfBoundsException.class);
    }
}
