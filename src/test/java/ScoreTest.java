import domain.CardSuitMap;
import domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreTest {
    @Test
    @DisplayName("A 카드는 11점으로 계산한다")
    void aceScore() {
        int score = CardSuitMap.getScore(0);

        assertEquals(11, score);
    }

    @Test
    @DisplayName("J Q K 카드는 10점으로 계산한다")
    void faceCardScore() {
        int score = CardSuitMap.getScore(10);

        assertEquals(10, score);
    }

    @Test
    @DisplayName("카드 점수 합계가 정상 계산된다")
    void totalScore() {
        Score score = new Score();
        score.addScore(CardSuitMap.getScore(0));
        score.addScore(CardSuitMap.getScore(10));

        assertEquals(21, score.getScore());
    }
}
