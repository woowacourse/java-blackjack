package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @DisplayName("카드를 52장 생성한다")
    @Test
    void makeCard() {
        Deck deck = new Deck(new RandomShuffleStrategy());
        int size = deck.getDeck().size();

        assertThat(size).isEqualTo(52);
    }
}
