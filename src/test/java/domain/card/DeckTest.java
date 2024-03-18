package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class DeckTest {

    @DisplayName("중복되지 않는 카드를 뽑는다.")
    @Test
    void draw() {
        final Deck deck = new Deck();
        final Set<Card> cards = new HashSet<>();

        for (int i = 0; i < 52; i++) {
            cards.add(deck.draw());
        }
        assertThat(cards).size().isEqualTo(52);
    }

    @DisplayName("52장이 넘는 카드를 뽑을 경우 예외를 발생한다.")
    @Test
    void deckSize() {
        final Deck deck = new Deck();

        for (int i = 0; i < 52; i++) {
            deck.draw();
        }
        assertThatCode(deck::draw).isInstanceOf(IllegalStateException.class);
    }
}
