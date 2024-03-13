package blackjack.model.result;

import blackjack.model.card.Card;
import blackjack.model.card.Denomination;
import blackjack.model.card.Suit;
import blackjack.model.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BettingBoardTest {
    @ParameterizedTest
    @MethodSource("provideMatchResultAndExpectedProfit")
    @DisplayName("플레이어의 게임 결과로 수익을 구한다")
    void determineProfitTest(MatchResult matchResult, double expectedProfit) {
        // given
        Player mia = new Player("mia", () -> new Card(Suit.HEART, Denomination.TEN));
        Map<Player, BettingMoney> bettingMoneys = Map.of(mia, new BettingMoney(1000));
        BettingBoard bettingBoard = new BettingBoard(bettingMoneys);

        // when
        BettingMoney bettingMoney = bettingBoard.determineProfit(mia, matchResult);

        // then
        assertThat(bettingMoney.getAmount()).isEqualTo(expectedProfit);
    }

    private static Stream<Arguments> provideMatchResultAndExpectedProfit() {
        return Stream.of(
                Arguments.of(MatchResult.WIN, 1000),
                Arguments.of(MatchResult.LOSE, -1000)
        );
    }
}
