package blackjack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    @DisplayName("두장의 카드를 지급받아 카드의 합을 계산한다.")
    void getTwoCards() {
        Card card1 = Card.valueOf(Suit.SPADE, Number.KING);
        Card card2 = Card.valueOf(Suit.SPADE, Number.ACE);
        Player player = new Player("jason", card1, card2);

        assertThat(player.countCards()).isEqualTo(21);
    }
}
