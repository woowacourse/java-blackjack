package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.user.BettingAmount;
import blackjack.domain.user.Nickname;
import blackjack.domain.user.Player;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerProfitTest {

    @Test
    @DisplayName("플레이어가 초기카드로 블랙잭을 만들 경우 구할 수 있다.")
    void canCreateWhenPlayerBlackjackWithInitialCard() {
        Player player = new Player(new Nickname("플레이이어"));
        player.registerBettingAmount(new BettingAmount(1000));

        PlayerProfit profit = PlayerProfit.createWhenPlayerBlackjackWithInitialCard(player);

        assertThat(profit.getNickname())
                .isEqualTo("플레이이어");
        assertThat(profit.getProfit())
                .isEqualTo(ProfitRate.BLACKJACK_WITH_INITIAL_HAND.calculateProfit(player.getBettingAmount()));
    }

    @Test
    @DisplayName("플레이어가 버스트일 경우 이익을 구할 수 있다.")
    void canCreatePlayerBust() {
        Player player = new Player(new Nickname("플레이이어"));
        player.registerBettingAmount(new BettingAmount(1000));

        PlayerProfit profit = PlayerProfit.createPlayerBust(player);

        assertThat(profit.getNickname())
                .isEqualTo("플레이이어");
        assertThat(profit.getProfit())
                .isEqualTo(ProfitRate.LOSE.calculateProfit(player.getBettingAmount()));
    }

    @Test
    @DisplayName("딜러가 버스트일 경우 이익을 구할 수 있다.")
    void canCreateWhenDealerBust() {
        Player player = new Player(new Nickname("플레이이어"));
        player.registerBettingAmount(new BettingAmount(1000));

        PlayerProfit profit = PlayerProfit.createWhenDealerBust(player);

        assertThat(profit.getNickname())
                .isEqualTo("플레이이어");
        assertThat(profit.getProfit())
                .isEqualTo(ProfitRate.WIN.calculateProfit(player.getBettingAmount()));
    }

    @ParameterizedTest
    @DisplayName("승패에 따라 이익을 구할 수 있다.")
    @MethodSource()
    void canCreateByWinningType(WinningType winningType) {
        Player player = new Player(new Nickname("플레이이어"));
        player.registerBettingAmount(new BettingAmount(1000));

        PlayerProfit profit = PlayerProfit.createByWinningType(player, winningType);

        ProfitRate expectedRate = winningType.getProfitRate();
        assertThat(profit.getNickname()).isEqualTo("플레이이어");
        assertThat(profit.getProfit()).isEqualTo(expectedRate.calculateProfit(player.getBettingAmount()));
    }

    static Stream<Arguments> canCreateByWinningType() {
        return Stream.of(
                Arguments.of(WinningType.WIN),
                Arguments.of(WinningType.LOSE),
                Arguments.of(WinningType.DRAW)
        );
    }
}