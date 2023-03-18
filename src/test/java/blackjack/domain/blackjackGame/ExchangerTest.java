package blackjack.domain.blackjackGame;

import blackjack.domain.person.BettingMoney;
import blackjack.domain.person.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ExchangerTest {

    @ParameterizedTest(name = "참가자의 베팅금액이 1000이고, 승패 결과가 {0}일 때 수익은 {1}이다.")
    @DisplayName("참가자의 이름과 승패결과를 가지고 수익을 계산할 수 있다.")
    @MethodSource("makeResult")
    void calculatePlayerProfit(GameResult gameResult, double expectedProfit) {
        // given
        Map<Player, Integer> bettingMoney = new HashMap<>();
        Player player = new Player("user");
        bettingMoney.put(player, 1000);
        Exchanger exchanger = new Exchanger(new BettingMoney(bettingMoney));

        // when
        double profit = exchanger.calculatePlayerProfit(player, gameResult);

        // then
        assertThat(profit)
                .isEqualTo(expectedProfit);
    }

    private static Stream<Arguments> makeResult() {
        return Stream.of(
                arguments(GameResult.BLACKJACK, 1500),
                arguments(GameResult.WIN, 1000),
                arguments(GameResult.DRAW, 0),
                arguments(GameResult.LOSE, -1000)
        );
    }

    @Test
    @DisplayName("참가자들의 수익으로 딜러의 수익을 계산할 수 있다.")
    void calculateDealerProfit() {
        // given
        Exchanger exchanger = new Exchanger(null);
        List<Double> playersProfit = List.of(1000.0, -2000.0);

        // when
        double dealerProfit = exchanger.calculateDealerProfit(playersProfit);

        // then
        assertThat(dealerProfit)
                .isEqualTo(1000);
    }
}