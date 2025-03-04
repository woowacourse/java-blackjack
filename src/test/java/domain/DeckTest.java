package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    @DisplayName("Card 종류별로 모아 Deck 을 만든다.")
    void test1() {
        Deck deck = new Deck();

        assertThat(deck.size()).isEqualTo(52);
    }
}
