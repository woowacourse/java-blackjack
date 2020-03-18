package blackjack.domain.playing.card;

import blackjack.domain.playing.card.exception.ScoreException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {
    @Test
    void Score() {
        assertThat(new Score(0)).isNotNull();
    }

    @Test
    void Score_UnderZero_ShouldThrowException() {
        assertThatThrownBy(() -> {
            new Score(-1);
        }).isInstanceOf(ScoreException.class);
    }

    @Test
    void add() {
        Score score = new Score(3);
        Score other = new Score(4);

        Score result = score.add(other);

        assertThat(result.getScore()).isEqualTo(7);
    }

    @ParameterizedTest
    @CsvSource(value = {"4:true", "2:false"}, delimiter = ':')
    void isUnder(int number, boolean result) {
        Score score = new Score(3);

        assertThat(score.isUnder(number)).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"3:true", "4:false"}, delimiter = ':')
    void isSame(int number, boolean result) {
        Score score = new Score(3);

        assertThat(score.isSame(number)).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"2:true", "4:false"}, delimiter = ':')
    void isOver(int number, boolean result) {
        Score score = new Score(3);

        assertThat(score.isOver(number)).isEqualTo(result);
    }
}