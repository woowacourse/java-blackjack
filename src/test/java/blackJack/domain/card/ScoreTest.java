package blackJack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ScoreTest {

    Score score;

    @ParameterizedTest(name = "점수가 burst인지 확인한다.")
    @CsvSource(value = {"20,false", "21,false", "22,true"})
    void isBurst(int value, boolean expected) {
        score = new Score(value);

        assertThat(score.isBurst()).isEqualTo(expected);
    }

    @ParameterizedTest(name = "점수가 21인지 확인한다.")
    @CsvSource(value = {"20,false", "21,true", "22,false"})
    void isBlackJack(int value, boolean expected) {
        score = new Score(value);

        assertThat(score.isBlackJack()).isEqualTo(expected);
    }

    @ParameterizedTest(name = "딜러가 hit이 가능한 점수인지 확인한다.")
    @CsvSource(value = {"16,true", "17,false"})
    void isDealerCanHit(int value, boolean expected) {
        score = new Score(value);

        assertThat(score.isDealerCanHit()).isEqualTo(expected);
    }

    @ParameterizedTest(name = "Ace가 11로 바뀌어야할 점수인지 확인한다.")
    @CsvSource(value = {"11,true", "12,false"})
    void isChangeAceScore(int value, boolean expected) {
        score = new Score(value);

        assertThat(score.isChangeAceScore()).isEqualTo(expected);
    }

    @Test
    @DisplayName("Ace의 점수를 11로 바꿔 계산한 점수를 반환한다.")
    void changeAceScore() {
        score = new Score(11);

        assertThat(score.changeAceScore()).isEqualTo(new Score(21));
    }
}