package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ResultTest {

    @Test
    void 게임결과는_생성시_각_결과를_0으로_가진다() {
        //given
        Result result = new Result();

        //when
        final int winCount = result.getResult().get(GameResult.WIN);
        final int loseCount = result.getResult().get(GameResult.LOSE);
        final int drawCount = result.getResult().get(GameResult.DRAW);

        //then
        assertThat(winCount).isZero();
        assertThat(loseCount).isZero();
        assertThat(drawCount).isZero();
    }

    @Test
    void 게임결과는_새로운_결과를_받으면_해당_결과의_카운트를_올린다() {
        //given
        Result result = new Result();

        //when
        Result newResult = result.updateResult(GameResult.WIN);
        final int winCount = newResult.getResult().get(GameResult.WIN);

        //then
        assertThat(winCount).isEqualTo(1);
    }
}
