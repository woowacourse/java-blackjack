package model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("덱에 카드가 남아있지 않다면 예외가 발생한다.")
    @Test
    void test1() {
        Deck deck = new Deck();
        for (int count = 0; count < 52; count++) {
            deck.getCard();
        }
        assertThatThrownBy(deck::getCard)
                .isInstanceOf(IllegalStateException.class);
    }

}
