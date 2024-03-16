package blackjack.model.betting;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.player.MatchResult;
import blackjack.view.dto.PlayerBettingProfitOutcome;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingTest {
    @Test
    @DisplayName("플레이어 이름으로 플레이어의 배팅 금액을 등록한다")
    void addPlayerBettingMoneyTest() {
        // given
        Betting betting = new Betting();
        String playerName = "dora";
        BettingMoney bettingMoney = new BettingMoney(1000);

        // when
        betting.addPlayerBettingMoney(playerName, bettingMoney);

        // then
        assertThat(betting.findBettingMoneyByPlayerName(playerName)).isEqualTo(bettingMoney);
    }

    @Test
    @DisplayName("승리한 플레이어는 배팅 금액의 1배를 추가로 받는다")
    void calculateWinningPlayerProfitTest() {
        // given
        Betting betting = new Betting();
        String playerName = "dora";
        BettingMoney bettingMoney = new BettingMoney(1000);
        betting.addPlayerBettingMoney(playerName, bettingMoney);

        // when
        PlayerBettingProfitOutcome outcome = betting.calculatePlayerBettingProfit(playerName, MatchResult.WIN);

        // then
        assertThat(outcome.profit()).isEqualTo(2000);
    }

    @Test
    @DisplayName("패배한 플레이어는 배팅 금액을 잃는다")
    void calculateLosingPlayerProfitTest() {
        // given
        Betting betting = new Betting();
        String playerName = "dora";
        BettingMoney bettingMoney = new BettingMoney(1000);
        betting.addPlayerBettingMoney(playerName, bettingMoney);

        // when
        PlayerBettingProfitOutcome outcome = betting.calculatePlayerBettingProfit(playerName, MatchResult.LOSE);

        // then
        assertThat(outcome.profit()).isEqualTo(0);
    }

    @Test
    @DisplayName("무승부인 플레이어는 배팅 금액을 돌려받는다")
    void calculatePushPlayerProfitTest() {
        // given
        Betting betting = new Betting();
        String playerName = "dora";
        BettingMoney bettingMoney = new BettingMoney(1000);
        betting.addPlayerBettingMoney(playerName, bettingMoney);

        // when
        PlayerBettingProfitOutcome outcome = betting.calculatePlayerBettingProfit(playerName, MatchResult.PUSH);

        // then
        assertThat(outcome.profit()).isEqualTo(1000);
    }

    @Test
    @DisplayName("블랙잭 승리한 플레이어는 배팅 금액의 1.5배를 추가로 받는다")
    void calculateBlackjackWinningPlayerProfitTest() {
        // given
        Betting betting = new Betting();
        String playerName = "dora";
        BettingMoney bettingMoney = new BettingMoney(1000);
        betting.addPlayerBettingMoney(playerName, bettingMoney);

        // when
        PlayerBettingProfitOutcome outcome = betting.calculatePlayerBettingProfit(playerName,
                MatchResult.BLACKJACK_WIN);

        // then
        assertThat(outcome.profit()).isEqualTo(2500);
    }
}
