package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @DisplayName("점수 반환")
    @Test
    void getScore() {
        Assertions.assertThat(Rank.TWO.getScore()).isEqualTo(2);
    }
}
