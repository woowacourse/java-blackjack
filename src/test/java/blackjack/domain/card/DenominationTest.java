package blackjack.domain.card;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Denomination.THREE;
import static blackjack.domain.card.Denomination.values;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DenominationTest {

    @DisplayName("끗수는 가중치를 조회할 수 있다.")
    @Test
    void 가중치_조회() {
        int score = ACE.getScore();

        assertThat(score).isEqualTo(1);
    }

    @DisplayName("끗수 갯수는 13개 이어야한다.")
    @Test
    void 끗수_갯수_확인() {
        int size = values().length;

        assertThat(size).isEqualTo(13);
    }

    @DisplayName("끗수 이름을 조회한다.")
    @Test
    void 끗수_이름_조회() {
        String name = KING.getName();

        assertThat(name).isEqualTo("K");
    }

    @DisplayName("현재 점수를 기반으로 현재 끗수의 숫자를 더해 반환한다.")
    @Test
    void 점수_더하기() {
        Denomination three = THREE;

        int result = three.addScore(10);

        assertThat(result).isEqualTo(13);
    }
}
