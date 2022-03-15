package blackJack.domain.result;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Suit;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WinDrawLoseTest {
    private final Player player = new Player("k");
    private final Dealer dealer = new Dealer();

    @Test
    @DisplayName("둘 다 21점 미만이면서 플레이어가 딜러보다 점수가 높은 경우")
    void playerWinByScore() {
        player.receiveCard(Card.from(Suit.CLOVER, Denomination.JACK));
        dealer.receiveCard(Card.from(Suit.CLOVER, Denomination.EIGHT));

        assertThat(WinDrawLose.calculateWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.WIN);
    }

    @Test
    @DisplayName("둘 다 21점 미만이면서 플레이어가 딜러와 점수가 같은 경우")
    void playerDrawByScore() {
        player.receiveCard(Card.from(Suit.CLOVER, Denomination.JACK));
        dealer.receiveCard(Card.from(Suit.DIAMOND, Denomination.JACK));

        assertThat(WinDrawLose.calculateWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.DRAW);
    }

    @Test
    @DisplayName("둘 다 21점 미만이면서 플레이어가 딜러보다 점수가 낮은 경우")
    void playerLoseByScore() {
        player.receiveCard(Card.from(Suit.CLOVER, Denomination.EIGHT));
        dealer.receiveCard(Card.from(Suit.CLOVER, Denomination.JACK));

        assertThat(WinDrawLose.calculateWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.LOSE);
    }

    @Test
    @DisplayName("플레이어만 버스트된 경우")
    void playerBust() {
        player.receiveCard(Card.from(Suit.CLOVER, Denomination.JACK));
        player.receiveCard(Card.from(Suit.DIAMOND, Denomination.JACK));
        player.receiveCard(Card.from(Suit.CLOVER, Denomination.TWO));
        dealer.receiveCard(Card.from(Suit.CLOVER, Denomination.EIGHT));

        assertThat(WinDrawLose.calculateWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.LOSE);
    }

    @Test
    @DisplayName("딜러만 버스트된 경우")
    void dealerBust() {
        player.receiveCard(Card.from(Suit.CLOVER, Denomination.EIGHT));
        dealer.receiveCard(Card.from(Suit.CLOVER, Denomination.JACK));
        dealer.receiveCard(Card.from(Suit.DIAMOND, Denomination.JACK));
        dealer.receiveCard(Card.from(Suit.CLOVER, Denomination.TWO));

        assertThat(WinDrawLose.calculateWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.WIN);
    }

    @Test
    @DisplayName("둘 다 버스트된 경우")
    void allBust() {
        player.receiveCard(Card.from(Suit.CLOVER, Denomination.JACK));
        player.receiveCard(Card.from(Suit.DIAMOND, Denomination.JACK));
        player.receiveCard(Card.from(Suit.CLOVER, Denomination.TWO));
        dealer.receiveCard(Card.from(Suit.HEART, Denomination.JACK));
        dealer.receiveCard(Card.from(Suit.SPADE, Denomination.JACK));
        dealer.receiveCard(Card.from(Suit.CLOVER, Denomination.TWO));

        assertThat(WinDrawLose.calculateWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.LOSE);
    }

    @Test
    @DisplayName("둘 다 21점이지만, 플레이어가 블랙잭인 경우")
    void playerWinByBlackJack() {
        player.receiveCard(Card.from(Suit.CLOVER, Denomination.JACK));
        player.receiveCard(Card.from(Suit.CLOVER, Denomination.ACE));
        dealer.receiveCard(Card.from(Suit.CLOVER, Denomination.JACK));
        dealer.receiveCard(Card.from(Suit.CLOVER, Denomination.NINE));
        dealer.receiveCard(Card.from(Suit.CLOVER, Denomination.TWO));

        assertThat(WinDrawLose.calculateWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.WIN);
    }

    @Test
    @DisplayName("둘 다 21점이고, 둘 다 블랙잭인 경우")
    void playerDrawByBlackJack() {
        player.receiveCard(Card.from(Suit.CLOVER, Denomination.JACK));
        player.receiveCard(Card.from(Suit.CLOVER, Denomination.ACE));
        dealer.receiveCard(Card.from(Suit.CLOVER, Denomination.JACK));
        dealer.receiveCard(Card.from(Suit.CLOVER, Denomination.ACE));

        assertThat(WinDrawLose.calculateWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.DRAW);
    }
}
