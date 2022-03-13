package blackjack.domain.card;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomDeckTest {

    @Test
    @DisplayName("덱은 48장의 카드를 가지고 있다")
    void deckSize() {
        RandomDeck deck = new RandomDeck();
        int size = deck.size();

        assertThat(size).isEqualTo(48);
    }

    @Test
    @DisplayName("한장의 카드를 뽑은 뒤 덱에 존재하는 카드는 한장 줄어들어야한다.")
    void draw() {
        RandomDeck deck = new RandomDeck();
        int size = deck.size();
        deck.draw();
        assertThat(deck.size()).isEqualTo(size - 1);
    }
}
