package deck;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("카드가 없을 때 카드를 뽑을 시 예외가 발생한다.")
    void test1() {
        // given
        Deck deck = new Deck(List::of);

        // when & then
        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalStateException.class);
    }
}
