package domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class ScoreTest {
    private static final Score BLACKJACK_SCORE = new Score(21);
    
    private Score tenScore;

    @BeforeEach
    void setUp() {
        tenScore = new Score(10);
    }
    
    @Test
    @DisplayName("21이 안넘는 경우 10을 더해서 반환한다.")
    void add() {
        Score score = tenScore.plusTenIfLessThenOrEqualTo(BLACKJACK_SCORE);
        assertThat(score).isEqualTo(new Score(20));
    }
    
    @Test
    @DisplayName("21을 넘는 경우 그대로 반환한다.")
    void notAdd() {
        Score score = new Score(15).plusTenIfLessThenOrEqualTo(BLACKJACK_SCORE);
        assertThat(score).isEqualTo(new Score(15));
    }
    
    @Test
    @DisplayName("같은 스코어인지 확인")
    void isSameScore() {
        assertThat(BLACKJACK_SCORE).isEqualTo(BLACKJACK_SCORE);
    }
    
    @Test
    @DisplayName("다른 스코어인지 확인")
    void isNotSameScore() {
        boolean isEquals = BLACKJACK_SCORE.equals(new Score(20));
        assertThat(isEquals).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {11, 12})
    @DisplayName("매개변수의 스코어보다 미만인 경우 true 반환")
    void isLessThen(int otherScore) {
        assertThat(tenScore.isLessThen(new Score(otherScore))).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {8, 9})
    @DisplayName("매개변수의 스코어보다 초과인 경우 true 반환")
    void isOverThen(int otherScore) {
        assertThat(tenScore.isOverThen(new Score(otherScore))).isTrue();
    }
}