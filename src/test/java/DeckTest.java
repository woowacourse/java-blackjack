import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
    @DisplayName("카드를 뽑을 수 있다.")
    @Test
    void test() {
        // given
        Deck deck = new Deck();

        // when
        Card card = deck.pick();

        // then
        assertThat(card).isInstanceOf(Card.class);
    }
}
