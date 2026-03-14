package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class BettingResultCalculatorTest {

    @Test
    void 플레이어들의_최종_수익_계산_테스트() {
        FinalResult normal = FinalResult.from(PlayerFixture.createDefault("pobi"), ResultType.WIN);
        FinalResult blackjack = FinalResult.from(PlayerFixture.createBlackjack("jun"), ResultType.BLACKJACK_WIN);
        FinalResult bust = FinalResult.from(PlayerFixture.createBust("min"), ResultType.LOSE);
        FinalResult draw = FinalResult.from(PlayerFixture.createDefault("kang"), ResultType.DRAW);

        TotalFinalResult totalFinalResult = TotalFinalResult.from(List.of(normal, blackjack, bust, draw));

        Map<Name, Integer> result = BettingResultCalculator.calculatePlayersProfit(totalFinalResult);

        assertThat(result).containsEntry(Name.from("pobi"), 10000)
                .containsEntry(Name.from("jun"), 15000)
                .containsEntry(Name.from("min"), -10000)
                .containsEntry(Name.from("kang"), 0);
    }

    @Test
    void 딜러의_총_수익_계산_테스트() {
        FinalResult normal = FinalResult.from(PlayerFixture.createDefault("pobi"), ResultType.WIN);
        FinalResult blackjack = FinalResult.from(PlayerFixture.createBlackjack("jun"), ResultType.BLACKJACK_WIN);
        FinalResult bust = FinalResult.from(PlayerFixture.createBust("min"), ResultType.LOSE);

        TotalFinalResult totalFinalResult = TotalFinalResult.from(List.of(normal, blackjack, bust));

        Map<Name, Integer> result = BettingResultCalculator.calculatePlayersProfit(totalFinalResult);

        assertThat(BettingResultCalculator.calculateDealerProfit(result)).isEqualTo(-15000);
    }
}