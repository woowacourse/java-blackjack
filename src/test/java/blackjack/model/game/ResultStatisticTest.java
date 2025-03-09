package blackjack.model.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.game.ResultStatistic.ResultCount;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ResultStatisticTest {

    private static Stream<Arguments> 결과가_여러개인지_확인한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                Result.WIN,
                                Result.DRAW
                        ),
                        true
                ),
                Arguments.of(
                        List.of(
                                Result.WIN,
                                Result.LOSE
                        ),
                        true
                ),
                Arguments.of(
                        List.of(
                                Result.DRAW
                        ),
                        false
                )
        );
    }

    @Test
    void 정렬된_결과_리스트로_초기화된다() {

        ResultStatistic resultStatistic = new ResultStatistic();

        Map<Result, ResultCount> expected = Map.of(
                Result.WIN, new ResultCount(),
                Result.DRAW, new ResultCount(),
                Result.LOSE, new ResultCount()
        );

        assertThat(resultStatistic.getStatistic()).containsAllEntriesOf(expected);
    }

    @Test
    void 결과를_하나_추가한다() {
        ResultStatistic resultStatistic = new ResultStatistic();
        resultStatistic.add(Result.WIN);

        Entry<Result, ResultCount> expected = Map.entry(Result.WIN, new ResultCount());
        expected.getValue().increase();

        assertThat(resultStatistic.getStatistic()).contains(expected);
    }

    @ParameterizedTest
    @MethodSource("결과가_여러개인지_확인한다_테스트_케이스")
    void 결과가_여러개인지_확인한다(final List<Result> results, final boolean expected) {
        ResultStatistic resultStatistic = new ResultStatistic();
        results.forEach(resultStatistic::add);

        assertThat(resultStatistic.hasMultipleResult()).isEqualTo(expected);
    }

    @Nested
    class ResultCountTest {

        @Test
        void 결과_카운트가_0으로_초기화된다() {
            ResultCount resultCount = new ResultCount();

            assertThat(resultCount.getValue()).isZero();
        }

        @Test
        void 결과_카운트가_증가한다() {
            ResultCount resultCount = new ResultCount();
            resultCount.increase();

            assertThat(resultCount.getValue()).isOne();
        }

        @Test
        void 의미있는_값이_있는지_확인한다() {
            ResultCount resultCount = new ResultCount();

            assertThat(resultCount.hasMeaningfulValue()).isFalse();

            resultCount.increase();

            assertThat(resultCount.hasMeaningfulValue()).isTrue();
        }
    }
}
