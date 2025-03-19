package blackjack.object;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.object.gambler.Name;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProfitCalculatorTest {
    @DisplayName("딜러의 합계가 21이 넘지 않는 경우 겜블러의 수익을 구한다.")
    @CsvSource(value = {"라젤:5000", "레오:15000", "비타:-5000",  "새로이:-10000", "꾹이:0", "딜러:-5000"}, delimiterString = ":")
    @ParameterizedTest
    void calculateGamblerProfit_DealerIsNotBust_Test(Name name, int expected) {
        // given
        Map<Name, Integer> bettingRecords = new HashMap<>(Map.of(
                new Name("라젤"), 5000,
                new Name("레오"), 10000,
                new Name("비타"), 5000,
                new Name("새로이"), 10000,
                new Name("꾹이"), 10000
        ));
        Map<Name, Integer> gamblerScores = Map.of(
                Name.getDealerName(), 18,
                new Name("라젤"), 19, // 딜러 패배
                new Name("레오"), 21, // 딜러 패배 (플레이어 블랙잭 승리)
                new Name("비타"), 17, // 딜러 승리
                new Name("새로이"), 30, // 딜러 승리 (플레이어 버스트)
                new Name("꾹이"), 18); // 무승부
        ProfitCalculator profitCalculator = new ProfitCalculator(gamblerScores, bettingRecords);

        // when
        Map<Name, Integer> result = profitCalculator.calculateGamblerProfit();

        // then
        assertThat(result.get(name)).isEqualTo(expected);
    }

    @DisplayName("딜러의 합계가 21이 넘는 경우 겜블러의 수익을 구한다.")
    @CsvSource(value = {"라젤:5000", "레오:15000", "비타:-5000",  "새로이:-10000", "딜러:-5000"}, delimiterString = ":")
    @ParameterizedTest
    void calculateGamblerProfit_DealerIsBust_Test(Name name, int expected) {
        // given
        Map<Name, Integer> bettingRecords = new HashMap<>(Map.of(
                new Name("라젤"), 5000,
                new Name("레오"), 10000,
                new Name("비타"), 5000,
                new Name("새로이"), 10000
        ));
        Map<Name, Integer> gamblerScores = Map.of(
                Name.getDealerName(), 24,
                new Name("라젤"), 19, // 딜러 패배
                new Name("레오"), 21, // 딜러 패배 (플레이어 블랙잭 승리)
                new Name("비타"), 24, // 딜러 승리 (플레이어 버스트, 딜러와 점수가 같은 경우)
                new Name("새로이"), 30); // 딜러 승리 (플레이어 버스트)
        ProfitCalculator profitCalculator = new ProfitCalculator(gamblerScores, bettingRecords);

        // when
        Map<Name, Integer> result = profitCalculator.calculateGamblerProfit();

        // then
        assertThat(result.get(name)).isEqualTo(expected);
    }
}
