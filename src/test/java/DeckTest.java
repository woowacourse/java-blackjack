import static org.assertj.core.api.Assertions.assertThat;

import domain.Card;
import domain.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Nested
    @DisplayName("카드를 순서대로 뽑는다.")
    class pickCard {

        @DisplayName("카드를 올바르게 뽑아온다.")
        @Test
        public void pickCard() throws Exception {
            // given 
            final var deck = new Deck();
            final var expected = new Card(1, "club");

            // when
            final var actual = deck.pickCard();

            // then
            assertThat(actual).isEqualTo(expected);
        }

    }
}
