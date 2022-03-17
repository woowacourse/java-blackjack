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
    @DisplayName("인자로 들어온 값보다 크면 isGreaterThan은 true를 반환한다.")
    void isGreaterThan() {
        //given
        Score score = new Score(1);

        //when
        boolean actual = score.isGreaterThan(0);

        //then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("isGreaterThan은 Score와도 비교가 가능하다.")
    void isLessThanScore() {
        //given
        Score score = new Score(1);

        //when
        boolean actual = score.isGreaterThan(new Score(0));

        //then
        assertThat(actual).isTrue();
    }
}
