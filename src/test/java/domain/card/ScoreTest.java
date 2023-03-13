package domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class ScoreTest {
    private Score tenScore;

    @BeforeEach
    void setUp() {
        tenScore = new Score(10);
    }
    
    @Test
    @DisplayName("음수가 들어오는 경우 예외 발생")
    void negative_exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Score(-1))
                .withMessage("Score에 음수는 들어올 수 없습니다.");
    }
    
    @Test
    @DisplayName("21이 안넘는 경우 10을 더해서 반환한다.")
    void add() {
        Score score = tenScore.plusTenIfNotBust();
        assertThat(score.getScore()).isEqualTo(20);
    }
    
    @Test
    @DisplayName("21을 넘는 경우 그대로 반환한다.")
    void notAdd() {
        Score score = new Score(15).plusTenIfNotBust();
        assertThat(score.getScore()).isEqualTo(15);
    }
    
    @Test
    @DisplayName("21을 넘는 경우 버스트")
    void bust() {
        boolean isBust = new Score(22).isBust();
        assertThat(isBust).isTrue();
    }
    
    @Test
    @DisplayName("21을 안넘는 경우 버스트 아님")
    void notBust() {
        boolean isBust = new Score(21).isBust();
        assertThat(isBust).isFalse();
    }
    
    @Test
    @DisplayName("같은 스코어인지 확인")
    void isSameScore() {
        boolean isSame = new Score(21).isSameTo(new Score(21));
        assertThat(isSame).isTrue();
    }
    
    @Test
    @DisplayName("다른 스코어인지 확인")
    void isNotSameScore() {
        boolean isSame = new Score(21).isSameTo(new Score(20));
        assertThat(isSame).isFalse();
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