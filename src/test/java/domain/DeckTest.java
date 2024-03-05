package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @DisplayName("52장의 카드를 가진 하나의 덱을 생성한다.")
    @Test
    void createCardDeck() {
        // given
        int expectedSize = 52;

        // when
        Deck deck = new Deck();

        // then
        assertThat(deck.getCards()).hasSize(expectedSize);
    }

    @DisplayName("카드를 뽑는다.")
    @Test
    void drawTest() {
        // given
        int expectedSize = 51;
        Deck deck = new Deck();

        // when
        deck.draw();

        // then
        assertThat(deck.getCards()).hasSize(expectedSize);
    }
}
