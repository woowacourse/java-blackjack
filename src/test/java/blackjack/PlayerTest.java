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

    @Test
    @DisplayName("플레이어의 합이 높을 경우 승리를 반환한다.")
    void playerIsLoseByOver21() {
        Card card1 = Card.valueOf(Suit.SPADE, Number.KING);
        Card card2 = Card.valueOf(Suit.SPADE, Number.ACE);
        Player player = new Player("jason", card1, card2);

        assertThat(player.isWin(20)).isEqualTo(Outcome.WIN);
    }

    @Test
    @DisplayName("플레이어의 합이 낮을 경우 승리를 반환한다.")
    void playerIsWinByDealerOver21() {
        Card card1 = Card.valueOf(Suit.SPADE, Number.NINE);
        Card card2 = Card.valueOf(Suit.SPADE, Number.ACE);
        Player player = new Player("jason", card1, card2);

        assertThat(player.isWin(21)).isEqualTo(Outcome.LOSE);
    }

    @Test
    @DisplayName("플레이어의 합과 같을 경우 무승부를 반환한다.")
    void playerIsDrawByDealerAndPlayerOver21() {
        Card card1 = Card.valueOf(Suit.SPADE, Number.KING);
        Card card2 = Card.valueOf(Suit.SPADE, Number.ACE);
        Player player = new Player("jason", card1, card2);

        assertThat(player.isWin(21)).isEqualTo(Outcome.DRAW);
    }
}
