package blackjack.domain.gameresult;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static blackjack.domain.card.Kind.SPADE;
import static blackjack.domain.card.Value.ACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class GameResultTest {
    private static Stream<Arguments> makeWinningPlayersAndTestDealer() {
        Map<Name, Batting> playerNamesAndBattings = new HashMap<>();
        playerNamesAndBattings.put(new Name("pobi"), Batting.from(100.0));
        playerNamesAndBattings.put(new Name("pobi"), Batting.from(50.0));
        Players testPlayers = new Players(playerNamesAndBattings);
        testPlayers.getPlayers()
                .forEach(player -> player.addCard(new Card(SPADE, ACE)));

        return Stream.of(arguments(testPlayers, new Dealer()));
    }

    @DisplayName("GameResult는 게임 베팅의 전체 수익 합산을 반환한다")
    @ParameterizedTest
    @MethodSource("makeWinningPlayersAndTestDealer")
    void should_returnSumOfProfit(Players players, Dealer dealer) {
        GameResult gameResult = GameResult.of(dealer, players);

        double expectedProfit = 150.0;
        double actualProfit = gameResult.getSumOfProfit();

        assertThat(actualProfit).isEqualTo(expectedProfit);
    }

    @DisplayName("GameResult는 플레이어별 베팅 결과를 반환한다")
    @ParameterizedTest
    @MethodSource("makeWinningPlayersAndTestDealer")
    void should_returnProfitResult(Players players, Dealer dealer) {
        List<Profit> actualProfitResult = GameResult.of(dealer, players)
                .getProfitResult()
                .values()
                .stream()
                .toList();

        assertThat(actualProfitResult.get(0)).isEqualTo(Profit.from(100.0));
        assertThat(actualProfitResult.get(1)).isEqualTo(Profit.from(50.0));
    }
}
