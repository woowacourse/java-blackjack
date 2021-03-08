package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WinnerCountTest {
    @Test
    @DisplayName("우승자 계산 확인")
    void calculateResult() {
        WinnerCount winnerCount = new WinnerCount();
        List<String> input = Arrays.asList("pobi", "jason", "cu");
        Players players = new Players(input, new Dealer());
        for (Player player : players.toList()) {
            player.matchResult(WinnerFlag.LOSE);
        }
        assertEquals(3, winnerCount.calculateTotalWinnings(players).get(WinnerFlag.LOSE));
    }
}
