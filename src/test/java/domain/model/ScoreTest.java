package domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import domain.type.Letter;
import domain.type.Suit;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("점수 추가를 테스트")
    public void testAddScore() {
        //given
        int initialValue = 1;
        Score score = new Score(initialValue);
        int additionalValue = 2;

        //when
        score.add(additionalValue);

        //then
        assertThat(score.getValue()).isEqualTo(initialValue + additionalValue);
    }
}