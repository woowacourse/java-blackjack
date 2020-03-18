package infra.repository;

import domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SingleDeckTest {
    @Test
    @DisplayName("#shuffle() : should return SingleDeck with 52 cards")
    void shuffle() {
        SingleDeck singleDeck = SingleDeck.shuffle();
        assertThat(singleDeck).isNotNull();
        assertThat(singleDeck.size()).isEqualTo(SingleDeck.SIZE);
    }

    @Test
    void pop() {
        SingleDeck singleDeck = SingleDeck.shuffle();
        assertThat(singleDeck.pop()).isInstanceOf(Card.class);
        assertThat(singleDeck.size()).isEqualTo(SingleDeck.SIZE - 1);
    }
}