package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @DisplayName("카드의 점수를 반환한다.")
    @Test
    void getScore() {
        Assertions.assertThat(Rank.TWO.getScore()).isEqualTo(2);
    }
}
