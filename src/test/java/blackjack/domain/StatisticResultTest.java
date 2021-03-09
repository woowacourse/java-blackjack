package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class StatisticResultTest {

    private static Stream<Arguments> generateCardAndResult() {
        return Stream.of(
            Arguments.of(Symbol.FOUR, Result.WIN),
            Arguments.of(Symbol.FIVE, Result.DRAW),
            Arguments.of(Symbol.SIX, Result.LOSE)
        );
    }

    @ParameterizedTest
    @MethodSource("generateCardAndResult")
    @DisplayName("딜러와 플레이어의 점수를 비교하고 결과를 반환한다.")
    public void getStatisticResult(Symbol symbol, Result result) {
        Dealer dealer = new Dealer();
        List<Player> players = Arrays.asList(
            new Player("jason")
        );
        dealer.receiveCard(new Card(Symbol.FIVE, Shape.CLOVER));
        players.get(0)
               .receiveCard(new Card(symbol, Shape.CLOVER));
        Participants participants = Participants.of(dealer, players);
        StatisticResult statisticResult = new StatisticResult(participants);
        Map<Result, Long> resultMap = statisticResult.aggregateDealerStatisticResult();
        assertThat(resultMap.get(result)).isEqualTo(1L);
    }
}
