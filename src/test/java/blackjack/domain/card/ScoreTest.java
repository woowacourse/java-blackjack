package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {

    @Test
    @DisplayName("Score 생성 테스트")
    void testInit(){
        Score score = new Score(13);
        assertThat(score).isEqualTo(new Score(13));
    }

    @Test
    @DisplayName("유효하지 않은 Score 생성 테스트")
    void testInvalidScoreValue(){
        assertThatThrownBy(()->{
            new Score(-1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("21이 넘었는지 확인한다.")
    void testIsBust(){
        Score score = new Score(21);
        assertThat(score.isBust()).isFalse();

        score = new Score(22);
        assertThat(score.isBust()).isTrue();
    }

    @Test
    @DisplayName("점수가 21로, 블랙잭인지 확인한다.")
    void testIsBlackJack(){
        Score score = new Score(13);
        assertThat(score.isBlackJack()).isFalse();

        score = new Score(21);
        assertThat(score.isBlackJack()).isTrue();
    }
}
