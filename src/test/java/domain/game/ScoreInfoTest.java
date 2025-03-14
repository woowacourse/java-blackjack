package domain.game;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ScoreInfoTest {

    @Test
    void 점수가_21점이고_카드갯수가_2개면_블랙잭() {
        ScoreInfo scoreInfo = new ScoreInfo(21, 2);
        assertThat(scoreInfo.isBlackJack()).isTrue();
    }

    @Test
    void 점수가_21점이고_카드갯수가_3개_이상이면_블랙잭_아님() {
        ScoreInfo scoreInfo = new ScoreInfo(21, 3);
        assertThat(scoreInfo.isBlackJack()).isFalse();
    }

    @Test
    void 점수가_21점이_넘어가면_버스트() {
        ScoreInfo scoreInfo = new ScoreInfo(22, 3);
        assertThat(scoreInfo.isBust()).isTrue();
    }

    @Test
    void 점수가_21점_이하면_버스트_아님() {
        ScoreInfo scoreInfo = new ScoreInfo(21, 3);
        assertThat(scoreInfo.isBust()).isFalse();
    }
}