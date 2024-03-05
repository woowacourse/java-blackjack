package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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

    @DisplayName("덱에 카드가 존재하는지 확인한다.")
    @Test
    void existTest() {
        // given
        int cardCount = 52;
        Deck deck = new Deck();

        // when
        boolean deckExist = deck.exist();

        for (int i = 0; i < cardCount; i++) {
            deck.draw();
        }

        boolean noDeck = deck.exist();

        // then
        assertAll(
                () -> assertThat(deckExist).isTrue(),
                () -> assertThat(noDeck).isFalse()
        );
    }
}
