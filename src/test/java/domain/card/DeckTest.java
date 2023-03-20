package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.strategy.RandomShuffleStrategy;

public class DeckTest {

    @DisplayName("카드를 52장 생성한다")
    @Test
    void makeCard() {
        Deck deck = new Deck(new RandomShuffleStrategy());
        int size = deck.getDeck().size();

        assertThat(size).isEqualTo(52);
    }
}
