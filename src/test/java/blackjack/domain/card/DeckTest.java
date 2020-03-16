package blackjack.domain.card;

import blackjack.domain.card.exception.DeckException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeckTest {
    private ProcessStrategy processStrategy;

    @BeforeEach
    void setUp() {
        processStrategy = new ShuffleStrategy();
    }

    @Test
    void create() {
        assertThat(Deck.create(processStrategy)).isNotNull();
    }

    @Test
    void draw() {
        Deck deck = Deck.create(processStrategy);

        Set<Card> set = new HashSet<>();

        for (int i = 0; i < 52; i++) {
            set.add(deck.draw());
        }

        assertThat(set.size())
                .isEqualTo(52);
    }

    @Test
    void draw_ThereAreNoCard_ShouldThrowException() {
        Deck deck = Deck.create(processStrategy);

        for (int i = 0; i < 52; i++) {
            deck.draw();
        }

        assertThatThrownBy(() -> {
            deck.draw();
        }).isInstanceOf(DeckException.class);
    }

    @Test
    void equals() {
        Deck deck1 = Deck.create(processStrategy);
        Deck deck2 = Deck.create(processStrategy);

        assertThat(deck1).isNotEqualTo(deck2);
    }
}