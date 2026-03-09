package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameResultTest {

    @ParameterizedTest
    @CsvSource(value = {"4,21", "20,21", "10,20"})
    void 점수_비교_시_게임_결과가_승리를_반환한다(int standard, int mine) {
        GameScore standardScore = new GameScore(standard);
        GameScore myScore = new GameScore(mine);
        // when
        GameResult gameResult = GameResult.judge(standardScore, myScore);
        // then
        assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    @ParameterizedTest
    @CsvSource(value = {"21,20", "20,17", "19,18"})
    void 점수_비교_시_게임_결과가_패배를_반환한다(int standard, int mine) {
        GameScore standardScore = new GameScore(standard);
        GameScore myScore = new GameScore(mine);
        // when
        GameResult gameResult = GameResult.judge(standardScore, myScore);
        // then
        assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @ParameterizedTest
    @CsvSource(value = {"21,21", "19,19"})
    void 점수_비교_시_게임_결과가_무승부를_반환한다(int standard, int mine) {
        GameScore standardScore = new GameScore(standard);
        GameScore myScore = new GameScore(mine);
        // when
        GameResult gameResult = GameResult.judge(standardScore, myScore);
        // then
        assertThat(gameResult).isEqualTo(GameResult.DRAW);
    }
}
