package domain;

import static domain.FinalGameResult.DRAW;
import static domain.FinalGameResult.LOSE;
import static domain.FinalGameResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import domain.participant.Dealer;
import domain.participant.Participant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class FinalGameResultTest {
    @ParameterizedTest
    @DisplayName("최종 게임 결과의 반대로 계산하는 기능")
    @MethodSource("provideFinalGameResult")
    void reverseResult(List<FinalGameResult> origin, List<FinalGameResult> expected) {

        assertThat(FinalGameResult.reverse(origin)).isEqualTo(expected);
    }

    public static Stream<Arguments> provideFinalGameResult() {
        return Stream.of(
                Arguments.of(generateList(WIN), generateList(LOSE)),
                Arguments.of(generateList(DRAW), generateList(DRAW)),
                Arguments.of(generateList(LOSE), generateList(WIN)),
                Arguments.of(generateList(WIN, WIN, DRAW), generateList(LOSE, LOSE, DRAW)),
                Arguments.of(generateList(WIN, DRAW, LOSE), generateList(LOSE, DRAW, WIN))
        );
    }

    private static List<FinalGameResult> generateList(FinalGameResult... result) {
        return Arrays.asList(result);
    }
}
