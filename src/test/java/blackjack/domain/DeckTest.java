package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    void 덱은_총52장의_카드를_가진다() {
        Deck deck = new Deck();
        assertThat(deck.getCount()).isEqualTo(52);
        assertThat(deck.getCount()).isNotEqualTo(51);
        assertThat(deck.getCount()).isNotEqualTo(53);
    }

    @Test
    void 덱에는_중복없이_52장의_카드가_있다() {
        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < 52; i++) {
            cards.add(deck.draw());
        }

        assertEquals(52, cards.size());
        assertEquals(52, new HashSet<>(cards).size());
    }

}
