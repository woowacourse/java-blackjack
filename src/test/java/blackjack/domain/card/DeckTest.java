package blackjack.domain.card;

import blackjack.domain.card.exceptions.DeckException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeckTest {
    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = Deck.create();
    }

    @Test
    void create() {
        assertThat(deck).isNotNull();
    }

    @Test
    void draw() {
        // given
        Set<Card> notDuplicated = new HashSet<>();
        for (int i = 0; i < 52; i++) {
            notDuplicated.add(deck.draw());
        }

        // then
        assertThat(notDuplicated.size())
                .isEqualTo(52);
    }

    @Test
    void draw_ThereAreNoCard_ShouldThrowException() {
        // given
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }

        // then
        assertThatThrownBy(deck::draw).
                isInstanceOf(DeckException.class);
    }

    @Test
    void shuffle() {
        // given
        Deck notExpected = Deck.create();

        // when
        deck.shuffle();

        // then
        assertThat(deck).isNotEqualTo(notExpected);
    }

    @Test
    void equals() {
        // given
        Deck expected = Deck.create();

        // then
        assertThat(deck).isEqualTo(expected);
    }

}