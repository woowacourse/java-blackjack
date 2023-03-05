package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ScoreTest {
    @DisplayName("생성테스트")
    @Test
    void create() {
        assertThatCode(() -> new Score(0))
                .doesNotThrowAnyException();
    }

    @DisplayName("점수를 반환할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 10})
    void getValue(int value) {
        //given
        Score score = new Score(value);
        //when
        int actual = score.getValue();
        //then
        assertThat(actual).isEqualTo(value);
    }

    @DisplayName("같은 값을 가진 객체는 같다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 21})
    void equals() {
        //given
        Score score1 = new Score(1);
        Score score2 = new Score(1);
        //when
        //then
        assertThat(score2).isEqualTo(score1);
    }
}