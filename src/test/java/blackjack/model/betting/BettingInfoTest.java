package blackjack.model.betting;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.gameRule.Result;
import blackjack.model.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingInfoTest {

    BettingInfo bettingInfo = new BettingInfo();

    @DisplayName("플레이어의 배팅 금액을 등록한다.")
    @Test
    void addPlayerBetAmount() {
        //given
        Player player = new Player("ted");
        int betAmount = 1000;

        //when
        bettingInfo.addPlayerBetAmount(player, betAmount);
        PlayerBetWallet playerBetWallet = bettingInfo.getBettingInfo().get(player);
        int playerBetAmount = playerBetWallet.getBetAmount();

        //then
        assertThat(playerBetAmount).isEqualTo(betAmount);
    }

    @DisplayName("플레이어의 수익 금액을 등록한다.")
    @Test
    void addPlayerProfitAmount() {
        //given
        Player player = new Player("ted");
        bettingInfo.addPlayerBetAmount(player, 1000);

        //when
        bettingInfo.addPlayerProfitAmount(player, Result.WIN);
        PlayerBetWallet playerBetWallet = bettingInfo.getBettingInfo().get(player);
        int playerProfitAmount = playerBetWallet.getProfitAmount();

        //then
        assertThat(playerProfitAmount).isEqualTo(2000);
    }

    @DisplayName("플레이어의 순수익을 계산한다.")
    @Test
    void calculatePlayerNetProfit() {
        //given
        Player player = new Player("ted");
        bettingInfo.addPlayerBetAmount(player, 1000);
        bettingInfo.addPlayerProfitAmount(player, Result.WIN);

        //when
        int playerNetProfit = bettingInfo.calculatePlayerNetProfit(player);

        //then
        assertThat(playerNetProfit).isEqualTo(1000);
    }

    @DisplayName("딜러의 순수익을 계산한다.")
    @Test
    void calculateDealerNetProfit() {
        //given
        Player player = new Player("ted");
        bettingInfo.addPlayerBetAmount(player, 1000);
        bettingInfo.addPlayerProfitAmount(player, Result.LOSE);

        //when
        int dealerNetProfit = bettingInfo.calculateDealerNetProfit();

        //then
        assertThat(dealerNetProfit).isEqualTo(1000);
    }
}
