package blackjack.domain.denomination;

import static blackjack.domain.Denomination.*;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Denomination;
import blackjack.domain.denomination.DenominationArgumentsProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

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

    @DisplayName("현재 점수를 기반으로 계산한다.")
    @ParameterizedTest
    @ArgumentsSource(DenominationArgumentsProvider.class)
    void 점수_계산(int beforeScore, Denomination denomination, int afterScore) {
        int result = denomination.addScore(beforeScore);

        assertThat(result).isEqualTo(afterScore);
    }
}
