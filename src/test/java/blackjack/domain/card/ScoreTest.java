package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ScoreTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1, 2, 3})
    void 불가능한_점수가_들어올_경우_예외처리(final int score) {
        assertThatThrownBy(() -> new Score(score))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("4미만의 점수는 존재하지 않습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"21,true", "20,false", "22,false"})
    void 블랙잭_점수인지_확인(final int inputScore, final boolean expected) {
        final Score score = new Score(inputScore);
        assertThat(score.isBlackjack()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"22,true", "20,false", "21,false"})
    void 버스트_점수인지_확인(final int inputScore, final boolean expected) {
        final Score score = new Score(inputScore);
        assertThat(score.isBust()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"21,true", "17,true", "16,false"})
    void 딜러_Stand점수인지_확인(final int inputScore, final boolean expected) {
        final Score score = new Score(inputScore);
        assertThat(score.isDealerStand()).isEqualTo(expected);
    }
}
