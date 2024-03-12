package blackjack.model.wallet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.gamer.Player;
import blackjack.model.result.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerBetInfoTest {

    PlayerBetInfo playerBetInfo = new PlayerBetInfo();

    @DisplayName("플레이어의 배팅 금액을 등록한다.")
    @Test
    void registerBetAmount() {
        //given
        Player player = Player.from("ted");
        int betAmount = 1000;

        //when
        playerBetInfo.registerBetAmount(player, betAmount);

        //then
        assertThat(playerBetInfo.playerBetAmount(player)).isEqualTo(betAmount);
    }

    @DisplayName("이미 배팅 금액이 등록된 플레이어의 배팅 금액을 등록할 경우 예외를 발생시킨다.")
    @Test
    void registerBetAmountByPlayerAlreadySet() {
        //given
        Player player = Player.from("ted");
        playerBetInfo.registerBetAmount(player, 1000);

        //when, then
        assertThatThrownBy(() -> playerBetInfo.registerBetAmount(player, 500))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어의 결과로 수익금을 등록한다.")
    @Test
    void registerProfitAmount() {
        //given
        Player player = Player.from("ted");
        playerBetInfo.registerBetAmount(player, 1000);
        Result playerResult = Result.BLACKJACK;

        //when
        playerBetInfo.registerProfitAmount(player, playerResult);
        int profitAmount = playerBetInfo.playerProfitAmount(player);

        //then
        assertThat(profitAmount).isEqualTo(1500);
    }

    @DisplayName("플레이어의 순수익을 반환한다.")
    @Test
    void playerNetProfit() {
        //given
        Player player = Player.from("ted");
        playerBetInfo.registerBetAmount(player, 1000);
        Result playerResult = Result.BLACKJACK;
        playerBetInfo.registerProfitAmount(player, playerResult);

        //when
        int netProfit = playerBetInfo.playerNetProfit(player);

        //then
        assertThat(netProfit).isEqualTo(500);
    }
}
