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
    
    @Test
    @DisplayName("한장의 카드를 추가한다.")
    void putCard() {
        Card card1 = Card.valueOf(Suit.SPADE, Number.NINE);
        Card card2 = Card.valueOf(Suit.SPADE, Number.ACE);
        Player player = new Player("jason", card1, card2);
        player.putCard(Card.valueOf(Suit.SPADE, Number.ACE));

        assertThat(player.countCards()).isEqualTo(21);
    }
}
