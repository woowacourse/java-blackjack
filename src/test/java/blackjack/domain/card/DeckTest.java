package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class DeckTest {

    @Test
    @DisplayName("52장의 카드가 매번 다른 것이 나온다.")
    void cardNotHaveDuplicate() {
        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>();
        for (int i = 1; i <= 52; i++) {
            cards.add(deck.draw());
        }
        Assertions.assertThat(cards).doesNotHaveDuplicates();
    }
}
