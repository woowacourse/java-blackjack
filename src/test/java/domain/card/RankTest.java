package domain.card;

import domain.result.score.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {
    @Test
    void 정적_팩토리_테스트() {
        assertThat(Rank.of("Ace")).isEqualTo(Rank.ACE);
    }


    @Test
    void 랭크별_점수_확인() {
        assertThat(Rank.KING.getScore()).isEqualTo(Score.of(10));
        assertThat(Rank.SEVEN.getScore()).isEqualTo(Score.of(7));
    }
}
