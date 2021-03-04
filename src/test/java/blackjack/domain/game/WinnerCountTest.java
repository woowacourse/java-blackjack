package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WinnerCountTest {
    @Test
    @DisplayName("우승자 계산 확인")
    void calculateResult() {
        WinnerCount winnerCount = new WinnerCount();
        Players players = new Players("pobi,jason,cu", new Dealer());
        for (Player player : players.getPlayers()) {
            player.matchResult(WinnerFlag.LOSE);
        }
        assertEquals(winnerCount.calculateTotalWinnings(players).get(WinnerFlag.LOSE), 3);
    }
}
