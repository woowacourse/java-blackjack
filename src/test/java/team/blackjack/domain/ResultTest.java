package team.blackjack.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import team.blackjack.domain.rule.DefaultBlackjackRule;

class ResultTest {

    @Test
    void WIN인_경우_반대_결과값은_LOSE가_출력된다() {
        Result result = Result.WIN;

        Assertions.assertEquals(Result.LOSE, result.reverse());
    }

    @Test
    void LOSE인_경우_반대_결과값은_WIN이_출력된다() {
        Result result = Result.LOSE;

        Assertions.assertEquals(Result.WIN, result.reverse());
    }

    @Test
    void DRAW인_경우_반대_결과값은_DRAW가_출력된다() {
        Result result = Result.DRAW;

        Assertions.assertEquals(Result.DRAW, result.reverse());
    }
}
