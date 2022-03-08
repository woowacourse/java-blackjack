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

    @DisplayName("끗수 갯수는 13개 이어야한다.")
    @Test
    void 끗수_갯수_확인() {
        int size = Denomination.values().length;

        assertThat(size).isEqualTo(13);
    }

    @DisplayName("끗수 이름을 조회한다.")
    @Test
    void 끗수_이름_조회() {
        String name = Denomination.KING.getName();

        assertThat(name).isEqualTo("K");
    }
}
