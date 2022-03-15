package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreTest {

    @Test
    @DisplayName("인자로 들어온 값보다 작으면 isLessThan은 true를 반환한다.")
    void isLessThan() {
        //given
        Score score = new Score(0);

        //when
        boolean actual = score.isLessThan(1);

        //then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("Score는 value 값을 기준으로 비교가 가능하다.")
    void compare() {
        //given
        Score greater = new Score(1);
        Score less = new Score(0);

        //when
        int actual = greater.compareTo(less);

        //then
        assertThat(actual).isEqualTo(1);
    }
}
