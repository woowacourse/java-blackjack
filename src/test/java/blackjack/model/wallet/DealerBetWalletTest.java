package blackjack.model.wallet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.model.gameRule.Result;
import blackjack.model.gamer.Name;
import blackjack.model.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerBetWalletTest {

    @DisplayName("플레이어들의 배팅 금액이 음수이면 예외를 발생시킨다.")
    @Test
    void createBetWalletByOutOfUnit() {
        //given
        int totalBetAmount = -1;

        //when, then
        assertThatThrownBy(() -> new DealerBetWallet(totalBetAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어에게 지급될 수익금이 딜러의 지급금에 추가된다.")
    @Test
    void registerPayoutAmount() {
        //given
        int playerBetAmount = 1000;
        Player player = new Player(new Name("ted"), playerBetAmount);
        DealerBetWallet dealerBetWallet = new DealerBetWallet(playerBetAmount);
        player.applyResult(Result.WIN);

        //when
        dealerBetWallet.registerPayoutAmount(player);
        int payoutAmount = dealerBetWallet.getPayoutAmount();

        //then
        assertThat(payoutAmount).isEqualTo(2000);
    }

    @DisplayName("플레이어들의 배팅 금액에서 지급금을 제외한 수익을 계산한다.")
    @Test
    void calculateNetProfit() {
        //given
        int player1BetAmount = 1000;
        Player player1 = new Player(new Name("ted1"), player1BetAmount);

        int player2BetAmount = 2000;
        Player player2 = new Player(new Name("ted2"), player2BetAmount);

        DealerBetWallet dealerBetWallet = new DealerBetWallet(player1BetAmount + player2BetAmount);
        player1.applyResult(Result.WIN);
        player2.applyResult(Result.LOSE);

        //when
        dealerBetWallet.registerPayoutAmount(player1);
        dealerBetWallet.registerPayoutAmount(player2);

        int payoutAmount = dealerBetWallet.getPayoutAmount();
        int netProfit = dealerBetWallet.calculateNetProfit();

        //then
        assertAll(
                () -> assertThat(payoutAmount).isEqualTo(2000),
                () -> assertThat(netProfit).isEqualTo(1000)
        );
    }
}
