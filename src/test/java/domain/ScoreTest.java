package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @Test
    @DisplayName("Score 캐싱을 확인한다.")
    void cachingCheck() {
        Score score = Score.of(21);

        assertThat(score == Score.of(21)).isTrue();
    }

    @Test
    @DisplayName("21 점이면 최고 점수이다.")
    void twentyOneIsMaxScore() {
        Score notMaxScore = Score.of(20);
        Score maxScore = Score.of(21);

        assertThat(notMaxScore.isMaxScore()).isFalse();
        assertThat(maxScore.isMaxScore()).isTrue();
    }

    @Test
    @DisplayName("21 점 보다 크면 Bust 이다.")
    void LargeThenTwentyOneIsBust() {
        Score notBust = Score.of(21);
        Score bust = Score.of(22);

        assertThat(notBust.isBust()).isFalse();
        assertThat(bust.isBust()).isTrue();
    }

    @Test
    @DisplayName("Dealer 가 16점 이하이면 Card 를 더 받을 수 있다.")
    void isPossibleDealerMoreCard() {
        Score moreCardAble = Score.of(16);
        Score moreCardNotAble = Score.of(17);

        assertThat(moreCardAble.isDealerMoreCardAble()).isTrue();
        assertThat(moreCardNotAble.isDealerMoreCardAble()).isFalse();
    }

    @Test
    @DisplayName("Player가 20점 이하이면 Card 를 더 받을 수 있다. (21점은 최고 점수이기에 자체적으로 막음)")
    void isPossiblePlayerMoreCard() {
        Score moreCardAble = Score.of(20);
        Score moreCardNotAble = Score.of(21);

        assertThat(moreCardAble.isPlayerMoreCardAble()).isTrue();
        assertThat(moreCardNotAble.isPlayerMoreCardAble()).isFalse();
    }

}
