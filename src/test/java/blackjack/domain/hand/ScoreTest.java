package blackjack.domain.hand;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;


class ScoreTest {

    @ParameterizedTest
    @ValueSource(ints = {22, 25, 50})
    @DisplayName("점수가 21점 초과이면 버스트이다")
    void isBustTest(int number) {
        // given
        Score score = new Score(number);

        // when & then
        assertThat(score.isBust()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {21, 15, 1})
    @DisplayName("점수가 21점 이하이면 버스트가 아니다")
    void isNotBustTest(int number) {
        // given
        Score score = new Score(number);

        // when & then
        assertThat(score.isBust()).isFalse();
    }
}
