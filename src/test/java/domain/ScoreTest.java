package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @Test
    void 점수가_상대보다_크면_승리다() {
        Score myScore = new Score(20, false);
        Score dealerScore = new Score(18, false);

        assertEquals(WinningStatus.WIN, myScore.compareTo(dealerScore));
    }

    @Test
    void 점수가_상대보다_작으면_패배다() {
        Score myScore = new Score(17, false);
        Score dealerScore = new Score(19, false);

        assertEquals(WinningStatus.LOSE, myScore.compareTo(dealerScore));
    }

    @Test
    void 점수가_같으면_무승부다() {
        Score myScore = new Score(18, false);
        Score dealerScore = new Score(18, false);

        assertEquals(WinningStatus.DRAW, myScore.compareTo(dealerScore));
    }

    @Test
    void 점수가_21초과면_버스트다() {
        Score score = new Score(22, false);

        assertTrue(score.isBurst());
    }

    @Test
    void 점수가_21이면_버스트가_아니다() {
        Score score = new Score(21, true);

        assertFalse(score.isBurst());
    }

    @Test
    void 블랙잭_여부를_반환한다() {
        Score blackJackScore = new Score(21, true);
        Score normalScore = new Score(21, false);

        assertAll(
                () -> assertTrue(blackJackScore.isBlackJack()),
                () -> assertFalse(normalScore.isBlackJack())
        );
    }
}

