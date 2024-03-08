package domain;

import java.util.HashSet;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    @DisplayName("중복되지 않는 카드를 뽑는다.")
    void draw() {
        Deck deck = new Deck();
        Set<Card> cards = new HashSet<>();
        for (int i = 0; i < 52; i++) {
            cards.add(deck.draw());
        }
        Assertions.assertThat(cards).size().isEqualTo(52);
    }

    @Test
    @DisplayName("52장이 넘는 카드를 뽑을 경우 예외를 발생한다.")
    void deckSize() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }
        Assertions.assertThatCode(deck::draw).isInstanceOf(IllegalStateException.class);
    }
}
