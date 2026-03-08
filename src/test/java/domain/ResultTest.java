package domain;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ResultTest {
    @ParameterizedTest
    @MethodSource("provideConditions")
    void 정확한_승패로_판정되어야_한다(int playerSum, int dealerSum, Result result){
        Result judgement = Result.judge(playerSum, dealerSum);
        Assertions.assertEquals(judgement, result);
    }

    static Stream<Arguments> provideConditions(){
        return Stream.of(
                Arguments.of(21, 20, Result.WIN),
                Arguments.of(24, 21, Result.LOSE),
                Arguments.of(19, 20, Result.LOSE),
                Arguments.of(20, 20, Result.DRAW),
                Arguments.of(23, 24, Result.DRAW)
        );
    }
}
