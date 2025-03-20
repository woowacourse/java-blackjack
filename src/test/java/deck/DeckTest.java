package deck;

import java.util.List;
import card.Card;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("덱이 없을 때 드로우하면 안된다.")
    void test1() {
        // given
        Deck deck = new Deck(new DeckShuffleStrategy() {
            @Override
            public List<Card> createAllCards() {
                return List.of();
            }
        });

        // when
        // then
        Assertions.assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalStateException.class);
    }
}
