package domain.state.finished;

import static org.assertj.core.api.Assertions.assertThat;

import domain.TestFixture;
import domain.card.vo.Rank;
import domain.state.Result;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FinishedTest {

    public static Stream<Arguments> earningRate() {
        return Stream.of(
                Arguments.of(new Stay(TestFixture.createHandByRank(List.of(Rank.KING, Rank.EIGHT))), Result.WIN, 10000,
                        10000),
                Arguments.of(new Stay(TestFixture.createHandByRank(List.of(Rank.TWO, Rank.EIGHT))), Result.DRAW, 10000,
                        0),
                Arguments.of(new Stay(TestFixture.createHandByRank(List.of(Rank.TWO, Rank.EIGHT))), Result.LOSE, 10000,
                        -10000),

                Arguments.of(new Bust(TestFixture.createHandByRank(List.of(Rank.TWO, Rank.EIGHT))), Result.WIN, 10000,
                        -10000),
                Arguments.of(new Bust(TestFixture.createHandByRank(List.of(Rank.TWO, Rank.EIGHT))), Result.DRAW, 10000,
                        -10000),
                Arguments.of(new Bust(TestFixture.createHandByRank(List.of(Rank.TWO, Rank.EIGHT))), Result.LOSE, 10000,
                        -10000),

                Arguments.of(new BlackJack(TestFixture.createHandByRank(List.of(Rank.TWO, Rank.EIGHT))), Result.WIN,
                        10000, 15000),
                Arguments.of(new BlackJack(TestFixture.createHandByRank(List.of(Rank.TWO, Rank.EIGHT))), Result.DRAW,
                        10000, 0)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("earningRate(Result, Integer): 결과에 따라 수익률을 반환한다.")
    void earningRate(Finished state, Result result, Integer betCost, Integer expected) {
        Integer earnCost = state.earningRate(result).apply(betCost);
        assertThat(earnCost).isEqualTo(expected);
    }
}
