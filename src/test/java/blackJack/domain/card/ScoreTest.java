package blackJack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ScoreTest {

    private Score score;

    @ParameterizedTest()
    @CsvSource(value = {"20,false", "21,false", "22,true"})
    void 점수가_Burst인지_확인한다(int value, boolean expected) {
        score = new Score(value);

        assertThat(score.isBurst()).isEqualTo(expected);
    }

    @ParameterizedTest()
    @CsvSource(value = {"20,false", "21,true", "22,false"})
    void 점수가_블랙잭인지_확인한다(int value, boolean expected) {
        score = new Score(value);

        assertThat(score.isBlackJack()).isEqualTo(expected);
    }

    @ParameterizedTest()
    @CsvSource(value = {"16,true", "17,false"})
    void 딜러가_카드를_가져올_수_있는_점수인지_확인한다(int value, boolean expected) {
        score = new Score(value);

        assertThat(score.isDealerCanHit()).isEqualTo(expected);
    }

    @ParameterizedTest()
    @CsvSource(value = {"11,true", "12,false"})
    void ACE를_11점으로_바꿔야하는_점수인지_확인한다(int value, boolean expected) {
        score = new Score(value);

        assertThat(score.isChangeAceScore()).isEqualTo(expected);
    }

    @Test
    void ACE의_점수를_11로_바꿔_계산한_점수를_반환한다() {
        score = new Score(11);

        assertThat(score.changeAceScore()).isEqualTo(new Score(21));
    }
}