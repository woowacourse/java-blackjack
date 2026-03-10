package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

public class TestScore {

    @Test
    public void 점수_불러오기_정상_작동() {
        Score score = new Score();

        assertThat(score.get()).isEqualTo(0);
    }

    @Test
    public void 점수_추가_정상_작동() {
        Score score = new Score();

        score.add(13);
        assertThat(score.get()).isEqualTo(13);
    }
}
