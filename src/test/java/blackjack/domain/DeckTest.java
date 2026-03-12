package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DeckTest {

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


    @Test
    void 카드를_한장_뽑으면_덱의_크기가_1줄어든다() {
        Deck deck = new Deck();

        deck.draw();

        assertThat(deck.getCount()).isEqualTo(51);
    }

    @Test
    void 카드를_모두_뽑으면_덱의_크기는_0이다() {
        Deck deck = new Deck();

        for (int i = 0; i < 52; i++) {
            deck.draw();
        }

        assertThat(deck.getCount()).isEqualTo(0);
    }
    
}
