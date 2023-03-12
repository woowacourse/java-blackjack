package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @Test
    @DisplayName("Score 캐싱을 확인한다.")
    void cachingCheck() {
        Score score = Score.ofPlayerScore(21, 0);

        assertThat(score == Score.ofPlayerScore(21, 0)).isTrue();
    }

    @Test
    @DisplayName("21 점이면 최고 점수이다.")
    void twentyOneIsMaxScore() {
        Score notMaxScore = Score.ofPlayerScore(20, 0);
        Score maxScore = Score.ofPlayerScore(21, 0);

        assertThat(notMaxScore.isMaxScore()).isFalse();
        assertThat(maxScore.isMaxScore()).isTrue();
    }

    @Test
    @DisplayName("21 점 보다 크면 Bust 이다.")
    void LargeThenTwentyOneIsBust() {
        Score notBust = Score.ofPlayerScore(21, 0);
        Score bust = Score.ofPlayerScore(22, 0);

        assertThat(notBust.isBust()).isFalse();
        assertThat(bust.isBust()).isTrue();
    }

    @Test
    @DisplayName("Dealer 가 16점 이하이면 Card 를 더 받을 수 있다.")
    void isPossibleDealerMoreCard() {
        Score moreCardAble = Score.ofPlayerScore(16, 0);
        Score moreCardNotAble = Score.ofPlayerScore(17, 0);

        assertThat(moreCardAble.isDealerMoreCardAble()).isTrue();
        assertThat(moreCardNotAble.isDealerMoreCardAble()).isFalse();
    }

    @Test
    @DisplayName("Player가 20점 이하이면 Card 를 더 받을 수 있다. (21점은 최고 점수이기에 자체적으로 막음)")
    void isPossiblePlayerMoreCard() {
        Score moreCardAble = Score.ofPlayerScore(20, 0);
        Score moreCardNotAble = Score.ofPlayerScore(21, 0);

        assertThat(moreCardAble.isPlayerMoreCardAble()).isTrue();
        assertThat(moreCardNotAble.isPlayerMoreCardAble()).isFalse();
    }

    @Test
    @DisplayName("Score가 ace의 개수를 보고, 유동적으로 점수를 조정해준다.")
    void decreaseScoreByAce() {
        Score score = Score.ofPlayerScore(44, 4);

        assertThat(score.getValue()).isEqualTo(14);
    }

}
