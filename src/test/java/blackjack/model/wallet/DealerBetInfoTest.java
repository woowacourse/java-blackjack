package blackjack.model.wallet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.model.gamer.Player;
import blackjack.model.result.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerBetInfoTest {

    PlayerBetInfo playerBetInfo = new PlayerBetInfo();

    @DisplayName("플레이어들의 배팅 금액이 음수이면 예외를 발생시킨다.")
    @Test
    void createBetWalletByOutOfUnit() {
        //given
        int totalBetAmount = -1;

        //when, then
        assertThatThrownBy(() -> DealerBetInfo.from(totalBetAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어에게 지급될 수익금이 딜러의 지급금에 추가된다.")
    @Test
    void registerPayoutAmount() {
        //given
        Player player = Player.from("ted");
        int playerBetAmount = 1000;

        playerBetInfo.registerBetAmount(player, playerBetAmount);
        DealerBetInfo dealerBetInfo = DealerBetInfo.from(playerBetAmount);

        //when
        playerBetInfo.registerProfitAmount(player, Result.WIN);
        dealerBetInfo.registerPayoutAmount(playerBetInfo, player);

        int payoutAmount = dealerBetInfo.getPayoutAmount();

        //then
        assertThat(payoutAmount).isEqualTo(2000);
    }

    @DisplayName("플레이어들의 배팅 금액에서 지급금을 제외한 수익을 계산한다.")
    @Test
    void calculateNetProfit() {
        //given
        Player player1 = Player.from("ted1");
        int player1BetAmount = 1000;

        Player player2 = Player.from("ted2");
        int player2BetAmount = 2000;

        playerBetInfo.registerBetAmount(player1, player1BetAmount);
        playerBetInfo.registerBetAmount(player2, player2BetAmount);
        DealerBetInfo dealerBetInfo = DealerBetInfo.from(player1BetAmount + player2BetAmount);

        //when
        playerBetInfo.registerProfitAmount(player1, Result.WIN);
        dealerBetInfo.registerPayoutAmount(playerBetInfo, player1);

        playerBetInfo.registerProfitAmount(player2, Result.LOSE);
        dealerBetInfo.registerPayoutAmount(playerBetInfo, player2);

        int payoutAmount = dealerBetInfo.getPayoutAmount();
        int netProfit = dealerBetInfo.calculateNetProfit();

        //then
        assertAll(
                () -> assertThat(payoutAmount).isEqualTo(2000),
                () -> assertThat(netProfit).isEqualTo(1000)
        );
    }
}
