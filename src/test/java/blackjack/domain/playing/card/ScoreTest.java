package blackjack.domain.playing.card;

import blackjack.domain.playing.card.exception.ScoreException;
import org.junit.jupiter.api.Test;

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

    @Test
    void isUnder_ShouldReturnTrue() {
        Score score = new Score(3);

        assertThat(score.isUnder(4)).isTrue();
    }

    @Test
    void isUnder_ShouldReturnFalse() {
        Score score = new Score(3);

        assertThat(score.isUnder(2)).isFalse();
    }

    @Test
    void isOver_ShouldReturnTrue() {
        Score score = new Score(3);

        assertThat(score.isOver(2)).isTrue();
    }

    @Test
    void isOver_ShouldReturnFalse() {
        Score score = new Score(3);

        assertThat(score.isOver(4)).isFalse();
    }
}