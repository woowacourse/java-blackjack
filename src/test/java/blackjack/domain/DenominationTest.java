package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DenominationTest {

    @DisplayName("끗수는 가중치를 조회할 수 있다.")
    @Test
    void 가중치_조회() {
        int score = Denomination.ACE.getScore();

        assertThat(score).isEqualTo(1);
    }
}
