package blackjack.domain.result;

import blackjack.domain.Money;
import blackjack.domain.ResultType;
import blackjack.domain.result.Results;
import blackjack.domain.user.Name;
import blackjack.domain.user.Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultsTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(Name.of("kimkim"), Money.of(10000));
    }

    private static Stream<Arguments> resultProvider() {
        return Stream.of(
                Arguments.of(ResultType.BLACKJACK_WIN, 15000),
                Arguments.of(ResultType.WIN, 10000),
                Arguments.of(ResultType.DRAW, 0),
                Arguments.of(ResultType.LOSE, -10000)
        );
    }

    @DisplayName("결과에 따라 베팅한 금액에 비례한 수익을 얻는다.")
    @ParameterizedTest
    @MethodSource("resultProvider")
    void computeProfitTest(ResultType resultType, int profit) {
        Results results = Results.of(new HashMap<Player, ResultType>(){
            {
                put(player, resultType);
            }
        });
        assertThat(results.getEarningMoneyOf(player).toLong()).isEqualTo(profit);
    }
}
