package blackJack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Symbol;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinOrLoseTest {

    private Dealer dealer = new Dealer();
    private Player player = new Player("rookie");

    @Test
    @DisplayName("플레이어의 점수가 블랙잭, 딜러의 점수가 14라 플레이어가 승리하는 경우")
    void calculatePlayerBlackJackWin() {
        player.receiveCard(new Card(Symbol.SPADE, Denomination.ACE));
        player.receiveCard(new Card(Symbol.SPADE, Denomination.JACK));
        dealer.receiveCard(new Card(Symbol.SPADE, Denomination.ACE));
        dealer.receiveCard(new Card(Symbol.SPADE, Denomination.THREE));

        assertThat(WinDrawLose.calculatePlayerWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.WIN);
    }

    @Test
    @DisplayName("플레이어의 점수가 20, 딜러의 점수가 14여서 플레이어가 승리하는 경우")
    void calculatePlayerScoreWin() {
        player.receiveCard(new Card(Symbol.SPADE, Denomination.TEN));
        player.receiveCard(new Card(Symbol.SPADE, Denomination.JACK));
        dealer.receiveCard(new Card(Symbol.SPADE, Denomination.ACE));
        dealer.receiveCard(new Card(Symbol.SPADE, Denomination.THREE));

        assertThat(WinDrawLose.calculatePlayerWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.WIN);
    }

    @Test
    @DisplayName("플레이어의 점수가 14, 딜러의 점수가 블랙잭이라 플레이가 패배하는 경우")
    void calculateDealerBlackJackLose() {
        player.receiveCard(new Card(Symbol.SPADE, Denomination.ACE));
        player.receiveCard(new Card(Symbol.SPADE, Denomination.THREE));
        dealer.receiveCard(new Card(Symbol.SPADE, Denomination.ACE));
        dealer.receiveCard(new Card(Symbol.SPADE, Denomination.JACK));

        assertThat(WinDrawLose.calculatePlayerWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.LOSE);
    }

    @Test
    @DisplayName("플레이어의 점수가 14, 딜러의 점수가 20여서 플레이어가 패배하는 경우")
    void calculatePlayerScoreLose() {
        player.receiveCard(new Card(Symbol.SPADE, Denomination.ACE));
        player.receiveCard(new Card(Symbol.SPADE, Denomination.THREE));
        dealer.receiveCard(new Card(Symbol.SPADE, Denomination.TEN));
        dealer.receiveCard(new Card(Symbol.SPADE, Denomination.JACK));

        assertThat(WinDrawLose.calculatePlayerWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.LOSE);
    }

    @Test
    @DisplayName("플레이어가 블랙잭, 딜러도 블랙잭이여서 플레이어가 무승부 경우")
    void calculatePlayerAndDealerScoreDraw() {
        player.receiveCard(new Card(Symbol.SPADE, Denomination.ACE));
        player.receiveCard(new Card(Symbol.SPADE, Denomination.JACK));
        dealer.receiveCard(new Card(Symbol.HEART, Denomination.ACE));
        dealer.receiveCard(new Card(Symbol.HEART, Denomination.JACK));

        assertThat(WinDrawLose.calculatePlayerWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.DRAW);
    }

    @Test
    @DisplayName("플레이어가 블랙잭, 딜러의 점수가 21점이여서 플레이어가 승리하는 경우")
    void calculatePlayerBlackJackAndDealerScoreWin() {
        player.receiveCard(new Card(Symbol.SPADE, Denomination.ACE));
        player.receiveCard(new Card(Symbol.SPADE, Denomination.JACK));
        dealer.receiveCard(new Card(Symbol.HEART, Denomination.THREE));
        dealer.receiveCard(new Card(Symbol.HEART, Denomination.EIGHT));
        dealer.receiveCard(new Card(Symbol.HEART, Denomination.JACK));

        assertThat(WinDrawLose.calculatePlayerWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.WIN);
    }

    @Test
    @DisplayName("플레이어가 21, 딜러가 블랙잭 이여서 플레이어가 패배하는 경우")
    void calculatePlayerScoreAndDealerBlackJackLose() {
        player.receiveCard(new Card(Symbol.HEART, Denomination.THREE));
        player.receiveCard(new Card(Symbol.HEART, Denomination.EIGHT));
        player.receiveCard(new Card(Symbol.HEART, Denomination.JACK));
        dealer.receiveCard(new Card(Symbol.SPADE, Denomination.ACE));
        dealer.receiveCard(new Card(Symbol.SPADE, Denomination.JACK));

        assertThat(WinDrawLose.calculatePlayerWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.LOSE);
    }

    @Test
    @DisplayName("플레이어가 버스트, 딜러의 점수가 20점이여서 플레이어가 패배하는 경우")
    void calculatePlayerBustAndDealerScoreLose() {
        player.receiveCard(new Card(Symbol.HEART, Denomination.KING));
        player.receiveCard(new Card(Symbol.HEART, Denomination.EIGHT));
        player.receiveCard(new Card(Symbol.HEART, Denomination.JACK));
        dealer.receiveCard(new Card(Symbol.SPADE, Denomination.TEN));
        dealer.receiveCard(new Card(Symbol.SPADE, Denomination.JACK));

        assertThat(WinDrawLose.calculatePlayerWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.LOSE);
    }

    @Test
    @DisplayName("플레이어의 점수가 20점, 딜러가 버스트여서 플레이어가 승리하는 경우")
    void calculatePlayerScoreAndDealerBustWinner() {
        player.receiveCard(new Card(Symbol.HEART, Denomination.KING));
        player.receiveCard(new Card(Symbol.HEART, Denomination.TEN));
        dealer.receiveCard(new Card(Symbol.SPADE, Denomination.TEN));
        dealer.receiveCard(new Card(Symbol.SPADE, Denomination.SIX));
        dealer.receiveCard(new Card(Symbol.SPADE, Denomination.KING));

        assertThat(WinDrawLose.calculatePlayerWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.WIN);
    }

    @Test
    @DisplayName("플레이어가 버스트, 딜러도 버스트여서 플레이어가 패배하는 경우")
    void calculatePlayerBustAndDealerBustLose() {
        player.receiveCard(new Card(Symbol.HEART, Denomination.KING));
        player.receiveCard(new Card(Symbol.HEART, Denomination.EIGHT));
        player.receiveCard(new Card(Symbol.HEART, Denomination.JACK));
        dealer.receiveCard(new Card(Symbol.SPADE, Denomination.TEN));
        dealer.receiveCard(new Card(Symbol.SPADE, Denomination.SIX));
        dealer.receiveCard(new Card(Symbol.SPADE, Denomination.KING));

        assertThat(WinDrawLose.calculatePlayerWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.LOSE);
    }
}
