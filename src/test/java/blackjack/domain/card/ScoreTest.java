package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ScoreTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1, 2, 3, 4})
    void 불가능한_점수가_들어올_경우_예외처리(final int score) {
        assertThatThrownBy(() -> new Score(score))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("4이하의 점수는 존재하지 않습니다.");
    }
}
