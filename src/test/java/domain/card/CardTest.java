package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @DisplayName("같은 수트와 끗수이면 같은 객체이다.")
    @Test
    void testCachingCardInstance() {
        Card card1 = Card.of(Denomination.TEN, Suits.DIAMOND);
        Card card2 = Card.of(Denomination.TEN, Suits.DIAMOND);

        assertThat(card1).isSameAs(card2);
    }
}
