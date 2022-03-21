package blackjack.domain.game;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

class PlayerWinningResultTest {

    @Test
    @DisplayName("플레이어 버스트면 패배")
    void getLoseResult() {
        Player player = new Player("player", 0);
        player.initCards(List.of(
            new Card(Suit.HEART, Denomination.TEN),
            new Card(Suit.SPADE, Denomination.JACK),
            new Card(Suit.SPADE, Denomination.QUEEN)
        ));
        Dealer dealer = new Dealer();
        dealer.initCards(List.of(
            new Card(Suit.DIAMOND, Denomination.THREE),
            new Card(Suit.CLOVER, Denomination.TEN)
        ));

        assertThat(PlayerWinningResult.of(dealer, player)).isEqualTo(PlayerWinningResult.LOSE);
    }

    @Test
    @DisplayName("플레이어만 블랙잭이면 우승")
    void getWinResult() {
        Player player = new Player("player", 0);
        player.initCards(List.of(
            new Card(Suit.HEART, Denomination.TEN),
            new Card(Suit.SPADE, Denomination.ACE)
        ));
        Dealer dealer = new Dealer();
        dealer.initCards(List.of(
            new Card(Suit.DIAMOND, Denomination.THREE),
            new Card(Suit.CLOVER, Denomination.TEN)
        ));

        assertThat(PlayerWinningResult.of(dealer, player)).isEqualTo(PlayerWinningResult.WIN);
    }

    @Test
    @DisplayName("딜러가 블랙잭 일 때 플레이어가 블랙잭이 아니면 패배")
    void getLoseResult2() {
        Player player = new Player("player", 0);
        player.initCards(List.of(
            new Card(Suit.HEART, Denomination.TEN),
            new Card(Suit.SPADE, Denomination.TEN)
        ));
        Dealer dealer = new Dealer();
        dealer.initCards(List.of(
            new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.CLOVER, Denomination.TEN)
        ));

        assertThat(PlayerWinningResult.of(dealer, player)).isEqualTo(PlayerWinningResult.LOSE);
    }

    @Test
    @DisplayName("딜러가 블랙잭 일 때 플레이어도 블랙잭이면 무승부")
    void getDraw() {
        Player player = new Player("player", 0);
        player.initCards(List.of(
            new Card(Suit.HEART, Denomination.ACE),
            new Card(Suit.SPADE, Denomination.TEN)
        ));
        Dealer dealer = new Dealer();
        dealer.initCards(List.of(
            new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.CLOVER, Denomination.TEN)
        ));

        assertThat(PlayerWinningResult.of(dealer, player)).isEqualTo(PlayerWinningResult.DRAW);
    }

    @Test
    @DisplayName("딜러보다 점수가 높을 때 승리")
    void getWinWithPoint() {
        Player player = new Player("player", 0);
        player.initCards(List.of(
            new Card(Suit.HEART, Denomination.ACE),
            new Card(Suit.SPADE, Denomination.SEVEN)
        ));
        Dealer dealer = new Dealer();
        dealer.initCards(List.of(
            new Card(Suit.DIAMOND, Denomination.SEVEN),
            new Card(Suit.CLOVER, Denomination.TEN)
        ));

        assertThat(PlayerWinningResult.of(dealer, player)).isEqualTo(PlayerWinningResult.WIN);
    }

    @Test
    @DisplayName("딜러와 점수가 같을 때 무승부")
    void getDrawWithPoint() {
        Player player = new Player("player", 0);
        player.initCards(List.of(
            new Card(Suit.HEART, Denomination.ACE),
            new Card(Suit.SPADE, Denomination.SEVEN)
        ));
        Dealer dealer = new Dealer();
        dealer.initCards(List.of(
            new Card(Suit.DIAMOND, Denomination.SEVEN),
            new Card(Suit.CLOVER, Denomination.ACE)
        ));

        assertThat(PlayerWinningResult.of(dealer, player)).isEqualTo(PlayerWinningResult.DRAW);
    }

    @Test
    @DisplayName("딜러보다 점수가 낮을 떄 패배")
    void getLoseWithPoint() {
        Player player = new Player("player", 0);
        player.initCards(List.of(
            new Card(Suit.HEART, Denomination.TEN),
            new Card(Suit.SPADE, Denomination.SEVEN)
        ));
        Dealer dealer = new Dealer();
        dealer.initCards(List.of(
            new Card(Suit.DIAMOND, Denomination.SEVEN),
            new Card(Suit.CLOVER, Denomination.ACE)
        ));

        assertThat(PlayerWinningResult.of(dealer, player)).isEqualTo(PlayerWinningResult.LOSE);
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 이겼을 경우 이익금 계산")
    void getProfitResultWhenBlackjack() {
        int profit = PlayerWinningResult.WIN.getBettingProfit(1000, true);

        assertThat(profit).isEqualTo(1500);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이 아니고 이겼을 경우 이익금 계산")
    void getProfitResultWhenWin() {
        int profit = PlayerWinningResult.WIN.getBettingProfit(1000, false);

        assertThat(profit).isEqualTo(1000);
    }

    @Test
    @DisplayName("플레이어가 비겼을 경우 이익금 계산")
    void getProfitResultWhenDraw() {
        int profit = PlayerWinningResult.DRAW.getBettingProfit(1000, false);

        assertThat(profit).isEqualTo(0);
    }

    @Test
    @DisplayName("플레이어가 졌을 경우 이익금 계산")
    void getProfitResultWhenLose() {
        int profit = PlayerWinningResult.LOSE.getBettingProfit(1000, false);

        assertThat(profit).isEqualTo(-1000);
    }
}