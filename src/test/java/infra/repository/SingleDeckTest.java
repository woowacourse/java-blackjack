package infra.repository;

import domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SingleDeckTest {
    @Test
    @DisplayName("#shuffle() : should return SingleDeck with 52 cards")
    void shuffle() {
        SingleDeck singleDeck = SingleDeck.shuffle();
        assertThat(singleDeck).isNotNull();
        assertThat(singleDeck.size()).isEqualTo(SingleDeck.SIZE);
    }

    @Test
    @DisplayName("#pop() : should return Card and size should be reduced")
    void popSucceed() {
        SingleDeck singleDeck = SingleDeck.shuffle();
        assertThat(singleDeck.pop()).isInstanceOf(Card.class);
        assertThat(singleDeck.size()).isEqualTo(SingleDeck.SIZE - 1);
    }

    @Test
    @DisplayName("#pop() : should throw IllegalStateException with empty cards")
    void popFailWithEmptyCards() {
        //given
        SingleDeck singleDeck = SingleDeck.shuffle();
        emptySingleDeck(singleDeck);

        //when & then
        assertThatThrownBy(singleDeck::pop)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(SingleDeck.EMPTY_DECK_MESSAGE);
    }

    private void emptySingleDeck(SingleDeck singleDeck) {
        for (int i = 0; i < SingleDeck.SIZE; i++) {
            singleDeck.pop();
        }
    }
}