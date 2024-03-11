package blackjack.model.wallet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.result.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerBetWalletTest {

    @DisplayName("배팅 금액이 100원 미만일 경우 예외를 발생시킨다.")
    @Test
    void createBetWalletByLowAmount() {
        //given
        int betAmount = 10;

        //when, then
        assertThatThrownBy(() -> PlayerBetWallet.createByBetAmount(betAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배팅 금액이 10원 단위가 아닐 경우 예외를 발생시킨다.")
    @Test
    void createBetWalletByOutOfUnit() {
        //given
        int betAmount = 15;

        //when, then
        assertThatThrownBy(() -> PlayerBetWallet.createByBetAmount(betAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("승리했을 때 받게 되는 금액을 계산한다.")
    @Test
    void calculatePayAmountByWin() {
        //given
        PlayerBetWallet playerBetWallet = PlayerBetWallet.createByBetAmount(100);
        Result winResult = Result.WIN;

        //when
        playerBetWallet.calculatePayAmount(winResult);
        int profitAmount = playerBetWallet.getProfitAmount();

        //then
        assertThat(profitAmount).isEqualTo(200);
    }

    @DisplayName("블랙잭일 때 받게 되는 금액을 계산한다.")
    @Test
    void calculatePayAmountByBlackjack() {
        //given
        PlayerBetWallet playerBetWallet = PlayerBetWallet.createByBetAmount(100);
        Result winResult = Result.BLACKJACK;

        //when
        playerBetWallet.calculatePayAmount(winResult);
        int profitAmount = playerBetWallet.getProfitAmount();

        //then
        assertThat(profitAmount).isEqualTo(150);
    }

    @DisplayName("푸시일 때 받게 되는 금액을 계산한다.")
    @Test
    void calculatePayAmountByPush() {
        //given
        PlayerBetWallet playerBetWallet = PlayerBetWallet.createByBetAmount(100);
        Result winResult = Result.PUSH;

        //when
        playerBetWallet.calculatePayAmount(winResult);
        int profitAmount = playerBetWallet.getProfitAmount();

        //then
        assertThat(profitAmount).isEqualTo(100);
    }

    @DisplayName("패배했을 때 받게 되는 금액을 계산한다.")
    @Test
    void calculatePayAmountByLose() {
        //given
        PlayerBetWallet playerBetWallet = PlayerBetWallet.createByBetAmount(100);
        Result winResult = Result.LOSE;

        //when
        playerBetWallet.calculatePayAmount(winResult);
        int profitAmount = playerBetWallet.getProfitAmount();

        //then
        assertThat(profitAmount).isEqualTo(0);
    }

    @DisplayName("결과가 없을 때 받게 되는 금액을 계산한다.")
    @Test
    void calculatePayAmountByNone() {
        //given
        PlayerBetWallet playerBetWallet = PlayerBetWallet.createByBetAmount(100);
        Result winResult = Result.NONE;

        //when
        playerBetWallet.calculatePayAmount(winResult);
        int profitAmount = playerBetWallet.getProfitAmount();

        //then
        assertThat(profitAmount).isEqualTo(0);
    }

    @DisplayName("배팅 금액 대비 수익을 계산한다.")
    @Test
    void calculateNetProfit() {
        //given
        PlayerBetWallet playerBetWallet = PlayerBetWallet.createByBetAmount(100);
        Result winResult = Result.WIN;

        //when
        playerBetWallet.calculatePayAmount(winResult);
        int netProfit = playerBetWallet.calculateNetProfit();

        //then
        assertThat(netProfit).isEqualTo(100);
    }
}
