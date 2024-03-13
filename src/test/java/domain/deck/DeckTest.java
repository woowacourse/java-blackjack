package domain.deck;

import domain.deck.strategy.SettedShuffleStrategy;
import domain.deck.strategy.RandomShuffleStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeckTest {

    private static final int DECK_SIZE = 312;

    @DisplayName("52 * 6개의 카드를 생성한다.")
    @Test
    void createDecksTest() {
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
    void emptyDecksTest() {
        // given
        SettedShuffleStrategy shuffleStrategy = new SettedShuffleStrategy(List.of());
        Deck deck = new Deck(shuffleStrategy);
        for(int i=0;i<DECK_SIZE;i++){
            deck.draw();
        }

        // then
        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드가 모두 소진되었습니다.");
    }
}
