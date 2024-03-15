package domain.deck;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.RandomShuffleStrategy;
import strategy.SettedShuffleStrategy;

import java.util.List;

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
        RandomShuffleStrategy shuffleStrategy = new RandomShuffleStrategy();
        Deck deck = new Deck(shuffleStrategy);

        // then
        assertThat(deck.getCards()).hasSize(expectedSize);
    }

    @DisplayName("52 * 6개의 카드를 생성한다.")
    @Test
    void drawDeckTest() {
        // given
        int expectedSize = DECK_SIZE - 1;
        RandomShuffleStrategy shuffleStrategy = new RandomShuffleStrategy();
        Deck deck = new Deck(shuffleStrategy);

        // when
        deck.draw();

        // then
        assertThat(deck.getCards()).hasSize(expectedSize);
    }

    @DisplayName("52 * 6개 이상 뽑으면 예외를 던진다.")
    @Test
    void emptyDeckTest() {
        // given
        SettedShuffleStrategy shuffleStrategy = new SettedShuffleStrategy(List.of());
        Deck deck = new Deck(shuffleStrategy);
        for (int i = 0; i < DECK_SIZE; i++) {
            deck.draw();
        }

        // then
        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드가 모두 소진되었습니다.");
    }
}
