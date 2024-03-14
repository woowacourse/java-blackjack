package blackjack.model.wallet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.gameRule.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerBetWalletTest {

    @DisplayName("배팅 금액으로 지갑을 생성한다.")
    @Test
    void createBetWallet() {
        //given
        int betAmount = 100;

        //when
        PlayerBetWallet playerBetWallet = new PlayerBetWallet(betAmount);

        //then
        assertThat(playerBetWallet.getBetAmount()).isEqualTo(betAmount);
    }

    @DisplayName("배팅 금액이 100원 미만일 경우 예외를 발생시킨다.")
    @Test
    void createBetWalletByLowAmount() {
        //given
        int betAmount = 10;

        //when, then
        assertThatThrownBy(() -> new PlayerBetWallet(betAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배팅 금액이 10원 단위가 아닐 경우 예외를 발생시킨다.")
    @Test
    void createBetWalletByOutOfUnit() {
        //given
        int betAmount = 15;

        //when, then
        assertThatThrownBy(() -> new PlayerBetWallet(betAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("승리했을 때 받게 되는 금액을 등록한다.")
    @Test
    void registerProfitAmountByWin() {
        //given
        PlayerBetWallet playerBetWallet = new PlayerBetWallet(100);
        Result winResult = Result.WIN;

        //when
        playerBetWallet.registerProfitAmount(winResult);
        int profitAmount = playerBetWallet.getProfitAmount();

        //then
        assertThat(profitAmount).isEqualTo(200);
    }

    @DisplayName("블랙잭일 때 받게 되는 금액을 등록한다.")
    @Test
    void registerProfitAmounttByBlackjack() {
        //given
        PlayerBetWallet playerBetWallet = new PlayerBetWallet(100);
        Result winResult = Result.BLACKJACK;

        //when
        playerBetWallet.registerProfitAmount(winResult);
        int profitAmount = playerBetWallet.getProfitAmount();

        //then
        assertThat(profitAmount).isEqualTo(150);
    }

    @DisplayName("푸시일 때 받게 되는 금액을 등록한다.")
    @Test
    void registerProfitAmountByPush() {
        //given
        PlayerBetWallet playerBetWallet = new PlayerBetWallet(100);
        Result winResult = Result.PUSH;

        //when
        playerBetWallet.registerProfitAmount(winResult);
        int profitAmount = playerBetWallet.getProfitAmount();

        //then
        assertThat(profitAmount).isEqualTo(100);
    }

    @DisplayName("패배했을 때 받게 되는 금액을 등록한다.")
    @Test
    void registerProfitAmountByLose() {
        //given
        PlayerBetWallet playerBetWallet = new PlayerBetWallet(100);
        Result winResult = Result.LOSE;

        //when
        playerBetWallet.registerProfitAmount(winResult);
        int profitAmount = playerBetWallet.getProfitAmount();

        //then
        assertThat(profitAmount).isEqualTo(0);
    }

    @DisplayName("결과가 없을 때 받게 되는 금액을 등록한다.")
    @Test
    void registerProfitAmountByNone() {
        //given
        PlayerBetWallet playerBetWallet = new PlayerBetWallet(100);
        Result winResult = Result.NONE;

        //when
        playerBetWallet.registerProfitAmount(winResult);
        int profitAmount = playerBetWallet.getProfitAmount();

        //then
        assertThat(profitAmount).isEqualTo(0);
    }

    @DisplayName("수익금을 두 번 이상등록하는 경우 예외를 발생시킨다.")
    @Test
    void registerProfitAmountByAlreadySet() {
        //given
        PlayerBetWallet playerBetWallet = new PlayerBetWallet(100);
        playerBetWallet.registerProfitAmount(Result.WIN);

        //when, then
        assertThatThrownBy(() -> playerBetWallet.registerProfitAmount(Result.LOSE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배팅 금액 대비 게임 수익을 계산한다.")
    @Test
    void calculateNetProfit() {
        //given
        PlayerBetWallet playerBetWallet = new PlayerBetWallet(100);
        Result winResult = Result.WIN;

        //when
        playerBetWallet.registerProfitAmount(winResult);
        int netProfit = playerBetWallet.calculateNetProfit();

        //then
        assertThat(netProfit).isEqualTo(100);
    }
}
