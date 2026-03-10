package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {
    @Test
    @DisplayName("A 카드는 기본 11점으로 계산한다")
    void aceScore() {
        Card ace = new Card(0);
        assertEquals(11, ace.getScore());
    }

    @Test
    @DisplayName("10, J, Q, K 카드는 10점으로 계산한다")
    void faceCardScore() {
        Card king = new Card(12);
        Card ten = new Card(9);

        assertEquals(10, king.getScore());
        assertEquals(10, ten.getScore());
    }

    @Test
    @DisplayName("Score 객체는 전달받은 점수값을 정확히 반환한다")
    void totalScore() {
        Score score = new Score(21);
        assertEquals(21, score.getGameScore());
    }
}