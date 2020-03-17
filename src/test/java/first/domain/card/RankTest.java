package first.domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {
    @Test
    void of테스트() {
        assertThat(Rank.of(1)).isEqualTo(Rank.ACE);
    }


    @Test
    void getScoreTest() {
        assertThat(Rank.K.getValue()).isEqualTo(10);
        assertThat(Rank.SEVEN.getValue()).isEqualTo(7);
    }
}