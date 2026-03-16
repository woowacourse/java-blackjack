package domain.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreTest {
    @Test
    @DisplayName("A 카드는 11점으로 계산한다")
    void aceScore() {
        int score = Rank.ACE.value();

        assertEquals(11, score);
    }

    @Test
    @DisplayName("J Q K 카드는 10점으로 계산한다")
    void faceCardScore() {
        int score = Rank.JACK.value();

        assertEquals(10, score);
    }

    @Test
    @DisplayName("점수 합계를 계산한다")
    void totalScore() {
        int score = 0;
        score += Rank.ACE.value();
        score += Rank.JACK.value();

        assertEquals(21, score);
    }
}
