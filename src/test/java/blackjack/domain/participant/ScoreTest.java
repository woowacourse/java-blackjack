package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreTest {

    @DisplayName("점수를 더한다")
    @Test
    public void add() {
        Score score1 = Score.from(1);
        Score score2 = Score.from(1);

        Score newScore = score1.add(score2);

        assertThat(newScore).isEqualTo(Score.from(2));
    }

    @DisplayName("점수가 버스트되지 않는지 반환한다")
    @Test
    public void isNotBurst() {
        Score notBurstScore = Score.from(21);
        Score burstScore = Score.from(22);

        assertThat(notBurstScore.isNotBurst()).isTrue();
        assertThat(burstScore.isNotBurst()).isFalse();
    }

    @DisplayName("점수가 21점인지 반환한다")
    @Test
    public void isBlackJackScore() {
        Score blackJackScore = Score.from(21);
        Score nonBlackJackScore = Score.from(22);

        assertThat(blackJackScore.isBlackJackScore()).isTrue();
        assertThat(nonBlackJackScore.isBlackJackScore()).isFalse();
    }

    @DisplayName("점수가 크거나 같은지 반환한다")
    @Test
    public void isBiggerThanOrEqual() {
        Score zero = Score.from(0);
        Score one = Score.from(1);

        assertThat(one.isBiggerThan(zero)).isTrue();
    }
}
