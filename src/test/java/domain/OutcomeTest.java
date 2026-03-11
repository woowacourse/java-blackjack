package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutcomeTest {
    @Test
    @DisplayName("점수가 같으면 무승부다")
    void drawResult() {
        Score dealerScore = new Score(20);
        Score playerScore = new Score(20);
        Outcome outcome = Outcome.decideWinner(playerScore, dealerScore);
        assertEquals(Outcome.DRAW, outcome);
    }

    @Test
    @DisplayName("딜러 점수가 더 크면 플레이어는 패배한다")
    void loseResult() {
        Score dealerScore = new Score(20);
        Score playerScore = new Score(18);
        Outcome outcome = Outcome.decideWinner(playerScore, dealerScore);
        assertEquals(Outcome.LOSE, outcome);
    }

    @Test
    @DisplayName("딜러가 버스트면 플레이어는 승리한다")
    void winWhenDealerBust() {
        Score dealerScore = new Score(22);
        Score playerScore = new Score(18);
        Outcome outcome = Outcome.decideWinner(playerScore, dealerScore);
        assertEquals(Outcome.WIN, outcome);
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 버스트인 경우 플레이어는 패배한다")
    void bothBust() {
        Score dealerScore = new Score(22);
        Score playerScore = new Score(23);
        assertEquals(Outcome.LOSE, Outcome.decideWinner(playerScore, dealerScore));
    }
}