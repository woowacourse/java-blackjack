package infra.repository;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SingleDeckTest {
    @Test
    void shuffle() {
        SingleDeck singleDeck=SingleDeck.shuffle();
        assertThat(singleDeck).isNotNull();
        assertThat(singleDeck.size()).isEqualTo(SingleDeck.SIZE);
    }
}