package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResultTest {
    @Test
    void 플레이어_점수가_21_이하이고_딜러보다_높으면_승리한다() {
        // given
        int playerSum = 21;
        int dealerSum = 20;

        // when
        Result judgement = Result.judge(playerSum, dealerSum);

        //then
        Assertions.assertEquals(Result.WIN, judgement);
    }

    @Test
    void 플레이어_점수가_21을_초과하면_패배한다() {
        // given
        int playerSum = 24;
        int dealerSum = 21;

        // when
        Result judgement = Result.judge(playerSum, dealerSum);

        //then
        Assertions.assertEquals(Result.LOSE, judgement);
    }

    @Test
    void 플레이어_점수가_21_이하이고_딜러보다_낮으면_패배한다() {
        // given
        int playerSum = 19;
        int dealerSum = 20;

        // when
        Result judgement = Result.judge(playerSum, dealerSum);

        //then
        Assertions.assertEquals(Result.LOSE, judgement);
    }

    @Test
    void 플레이어와_딜러의_점수가_같고_21_이하하면_무승부이다() {
        // given
        int playerSum = 20;
        int dealerSum = 20;

        // when
        Result judgement = Result.judge(playerSum, dealerSum);

        //then
        Assertions.assertEquals(Result.DRAW, judgement);
    }

    @Test
    void 플레이어와_딜러가_21을_초과하면_무승부이다() {
        // given
        int playerSum = 23;
        int dealerSum = 24;

        // when
        Result judgement = Result.judge(playerSum, dealerSum);

        //then
        Assertions.assertEquals(Result.DRAW, judgement);
    }
}
