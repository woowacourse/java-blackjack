package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러의 카드 총합이 17보다 작을 경우 카드를 계속 받는다.")
    void under16_dealerGetCard() {
        Dealer dealer = new Dealer(List.of(new Card(CardNumber.TEN, Symbol.SPADE), new Card(CardNumber.SIX,
                Symbol.SPADE)));
        assertThat(dealer.isFinished()).isFalse();
    }

    @Test
    @DisplayName("딜러의 카드 총합이 17 이상일 경우 카드를 받을 수 없다.")
    void over16_dealerCannotGetCard() {
        Dealer dealer = new Dealer(List.of(new Card(CardNumber.TEN, Symbol.SPADE), new Card(CardNumber.SEVEN,
                Symbol.SPADE)));
        assertThat(dealer.isFinished()).isTrue();
    }

    @Test
    @DisplayName("플레이어만 버스트되면 딜러는 승리한다")
    void playerIsBust_dealerWin() {
        Player player = new Player("dog",
                List.of(new Card(CardNumber.TEN, Symbol.SPADE), new Card(CardNumber.SEVEN, Symbol.SPADE)));
        player.receiveCard(new Card(CardNumber.FIVE, Symbol.SPADE));
        Dealer dealer = new Dealer(
                List.of(new Card(CardNumber.TEN, Symbol.SPADE), new Card(CardNumber.SEVEN, Symbol.SPADE)));
        assertThat(dealer.judgeResult(player)).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("딜러만 블랙잭이면 딜러는 승리한다")
    void onlyDealerIsBlackJack_dealerWin() {
        Player player = new Player("dog",
                List.of(new Card(CardNumber.TEN, Symbol.SPADE), new Card(CardNumber.SEVEN, Symbol.SPADE)));
        player.receiveCard(new Card(CardNumber.FOUR, Symbol.SPADE));
        Dealer dealer = new Dealer(
                List.of(new Card(CardNumber.KING, Symbol.SPADE), new Card(CardNumber.ACE, Symbol.SPADE)));
        assertThat(dealer.judgeResult(player)).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("딜러만 버스트라면 딜러가 패배한다")
    void dealerIsBust_dealerLose() {
        Player player = new Player("dog",
                List.of(new Card(CardNumber.TEN, Symbol.SPADE), new Card(CardNumber.SEVEN, Symbol.SPADE)));
        player.receiveCard(new Card(CardNumber.THREE, Symbol.SPADE));
        Dealer dealer = new Dealer(
                List.of(new Card(CardNumber.TEN, Symbol.SPADE), new Card(CardNumber.SEVEN, Symbol.SPADE)));
        dealer.receiveCard(new Card(CardNumber.FIVE, Symbol.CLOVER));
        assertThat(dealer.judgeResult(player)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러, 플레이어가 둘 다 버스트가 아니라면, 숫자가 클 때 승리한다")
    void bothNotBust_dealerScoreHigher_win() {
        Player player = new Player("dog",
                List.of(new Card(CardNumber.TEN, Symbol.SPADE), new Card(CardNumber.SIX, Symbol.SPADE)));
        player.receiveCard(new Card(CardNumber.TWO, Symbol.CLOVER));
        Dealer dealer = new Dealer(
                List.of(new Card(CardNumber.ACE, Symbol.SPADE), new Card(CardNumber.SIX, Symbol.SPADE)));
        dealer.receiveCard(new Card(CardNumber.TWO, Symbol.CLOVER));
        assertThat(dealer.judgeResult(player)).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("딜러, 플레이어 점수가 동일하면 무승부다")
    void bothNotBust_equalScore_draw() {
        Player player = new Player("dog",
                List.of(new Card(CardNumber.SIX, Symbol.SPADE), new Card(CardNumber.SEVEN, Symbol.SPADE)));
        player.receiveCard(new Card(CardNumber.FIVE, Symbol.CLOVER));
        Dealer dealer = new Dealer(
                List.of(new Card(CardNumber.THREE, Symbol.SPADE), new Card(CardNumber.FOUR, Symbol.SPADE)));
        dealer.receiveCard(new Card(CardNumber.ACE, Symbol.CLOVER));
        assertThat(dealer.judgeResult(player)).isEqualTo(GameResult.DRAW);
    }
}
