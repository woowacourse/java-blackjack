package model.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

public class ScoreTest {

    @Test
    public void 점수_불러오기_정상_작동() {
        Score score = new Score();

        assertThat(score.getScore()).isEqualTo(0);
    }

    @Test
    public void 점수_추가_정상_작동() {
        Score score = new Score();

        score.add(13);
        assertThat(score.getScore()).isEqualTo(13);
    }
}
