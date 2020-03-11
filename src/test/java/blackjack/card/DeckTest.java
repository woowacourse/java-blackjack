package blackjack.card;

import blackjack.card.exception.DeckException;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeckTest {
    @Test
    void create() {
        assertThat(Deck.create()).isNotNull();
    }

    @Test
    void draw() {
        Deck deck = Deck.create();

        Set<Card> set = new HashSet<>();

        for (int i = 0; i < 52; i++) {
            set.add(deck.draw());
        }

        assertThat(set.size())
                .isEqualTo(52);
    }

    @Test
    void draw_ThereAreNoCard_ShouldThrowException() {
        Deck deck = Deck.create();

        for (int i = 0; i < 52; i++) {
            deck.draw();
        }

        assertThatThrownBy(() -> {
            deck.draw();
        }).isInstanceOf(DeckException.class);
    }

    @Test
    void shuffle() {
        Deck deck1 = Deck.create();
        Deck deck2 = Deck.create();

        deck1.shuffle();

        assertThat(deck1).isNotEqualTo(deck2);
    }

    @Test
    void equals() {
        Deck deck1 = Deck.create();
        Deck deck2 = Deck.create();

        assertThat(deck1).isEqualTo(deck2);
    }

}