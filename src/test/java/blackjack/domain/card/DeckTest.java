package blackjack.domain.card;

import blackjack.domain.card.exception.DeckException;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeckTest {
    @Test
    void createWithShuffle() {
        assertThat(Deck.createWithShuffle()).isNotNull();
    }

    @Test
    void draw() {
        Deck deck = Deck.createWithShuffle();

        Set<Card> set = new HashSet<>();

        for (int i = 0; i < 52; i++) {
            set.add(deck.draw());
        }

        assertThat(set.size())
                .isEqualTo(52);
    }

    @Test
    void draw_ThereAreNoCard_ShouldThrowException() {
        Deck deck = Deck.createWithShuffle();

        for (int i = 0; i < 52; i++) {
            deck.draw();
        }

        assertThatThrownBy(() -> {
            deck.draw();
        }).isInstanceOf(DeckException.class);
    }

    @Test
    void equals() {
        Deck deck1 = Deck.createWithShuffle();
        Deck deck2 = Deck.createWithShuffle();

        assertThat(deck1).isNotEqualTo(deck2);
    }
}