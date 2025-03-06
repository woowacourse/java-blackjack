package model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("덱에 카드가 남아있지 않다면 예외가 발생한다.")
    @Test
    void test1() {
        Deck deck = new Deck(new ArrayList<>(List.of()));
        assertThatThrownBy(deck::getCard)
                .isInstanceOf(IllegalStateException.class);
    }

}
