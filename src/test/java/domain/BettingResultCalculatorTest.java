package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class BettingResultCalculatorTest {
    FinalResult normal = FinalResult.from(
            Player.of(Name.from("pobi"), new Betting(10000),
                    new Hand(List.of(
                            Card.of(CardNumber.J, CardShape.CLOVER),
                            Card.of(CardNumber.Q, CardShape.CLOVER)))), ResultType.WIN);

    FinalResult blackjack = FinalResult.from(
            Player.of(Name.from("jun"), new Betting(10000),
                    new Hand(List.of(
                            Card.of(CardNumber.J, CardShape.CLOVER),
                            Card.of(CardNumber.ACE, CardShape.CLOVER)))), ResultType.BLACKJACK_WIN);

    FinalResult bust = FinalResult.from(
            Player.of(Name.from("min"), new Betting(10000),
                    new Hand(List.of(
                            Card.of(CardNumber.J, CardShape.CLOVER),
                            Card.of(CardNumber.Q, CardShape.CLOVER),
                            Card.of(CardNumber.K, CardShape.DIAMOND)))), ResultType.LOSE);

    @Test
    void 플레이어들의_최종_수익_계산_테스트() {
        TotalFinalResult totalFinalResult = TotalFinalResult.from(List.of(normal, blackjack, bust));

        Map<Name, Integer> result = BettingResultCalculator.calculatePlayersProfit(totalFinalResult);

        assertThat(result).containsEntry(Name.from("pobi"), 10000)
                .containsEntry(Name.from("jun"), 15000)
                .containsEntry(Name.from("min"), -10000);
    }

    @Test
    void 딜러의_총_수익_계산_테스트() {
        TotalFinalResult totalFinalResult = TotalFinalResult.from(List.of(normal, blackjack, bust));
        Map<Name, Integer> result = BettingResultCalculator.calculatePlayersProfit(totalFinalResult);

        assertThat(BettingResultCalculator.calculateDealerProfit(result)).isEqualTo(-15000);
    }
}