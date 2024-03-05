package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
    @Test
    @DisplayName("중복되지 않는 카드를 뽑는다.")
    void poll() {
        Deck deck = new Deck();
        Set<Card> cards = new HashSet<>();
        for (int i = 0; i < 58; i++) {
            cards.add(deck.poll());
        }
        Assertions.assertThat(cards).size().isEqualTo(58);
    }
}
