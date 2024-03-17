package domain.deck;

import domain.card.CardFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.RandomShuffleStrategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeckTest {

    private static final int DECK_SIZE = 312;

    @DisplayName("52 * 6개의 카드를 생성한다.")
    @Test
    void createDeckTest() {
        // given
        int expectedSize = DECK_SIZE;

        // when
        CardFactory cardFactory = new CardFactory(new RandomShuffleStrategy());
        Deck deck = new Deck(cardFactory);

        // then
        assertThat(deck.getCards()).hasSize(expectedSize);
    }

    @DisplayName("52 * 6개의 카드를 생성한다.")
    @Test
    void drawDeckTest() {
        // given
        int expectedSize = DECK_SIZE - 1;
        CardFactory cardFactory = new CardFactory(new RandomShuffleStrategy());
        Deck deck = new Deck(cardFactory);

        // when
        deck.draw();

        // then
        assertThat(deck.getCards()).hasSize(expectedSize);
    }

    @DisplayName("52 * 6개 이상 뽑으면 예외를 던진다.")
    @Test
    void emptyDeckTest() {
        // given
        CardFactory cardFactory = new CardFactory(new RandomShuffleStrategy());
        Deck deck = new Deck(cardFactory);
        for (int i = 0; i < DECK_SIZE; i++) {
            deck.draw();
        }

        // then
        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드가 모두 소진되었습니다.");
    }
}
