package domain.card;

import domain.result.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {
    @Test
    void of테스트() {
        assertThat(Rank.of("Ace")).isEqualTo(Rank.ACE);
    }


    @Test
    void getScoreTest() {
        assertThat(Rank.KING.getScore()).isEqualTo(new Score(10));
        assertThat(Rank.SEVEN.getScore()).isEqualTo(new Score(7));
    }
}