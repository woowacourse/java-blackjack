package blackjack.model.game;

import static blackjack.model.game.PlayerResult.BLACKJACK;
import static blackjack.model.game.PlayerResult.DRAW;
import static blackjack.model.game.PlayerResult.LOSE;
import static blackjack.model.game.PlayerResult.WIN;

import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import java.util.HashMap;
import java.util.Map;

public class GameResult {
    private final Map<Player, PlayerResult> winLoseResult;

    public GameResult(Dealer dealer, Players players) {
        Map<Player, PlayerResult> winLoseResult = new HashMap<>();
        players.getParticipants().forEach(
                participant -> {
                    winLoseResult.put(participant, getParticipantResult(dealer, participant));
                }
        );
        this.winLoseResult = winLoseResult;
    }

    private PlayerResult getParticipantResult(Dealer dealer, Player player) {
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        int dealerPoint = dealer.calculatePoint();
        int participantPoint = player.calculatePoint();
        if (participantPoint == 21 && dealerPoint == 21) {
            return DRAW;
        }
        if (participantPoint == 21) {
            return BLACKJACK;
        }
        if (dealerPoint > participantPoint) {
            return LOSE;
        }
        if (dealerPoint < participantPoint) {
            return WIN;
        }
        return DRAW;
    }

    public Map<Player, PlayerResult> getWinLoseResult() {
        return winLoseResult;
    }

    public int getDealerWinCount() {
        return (int) winLoseResult.values().stream()
                .filter(result -> result == LOSE)
                .count();
    }

    public int getDealerLoseCount() {
        return (int) winLoseResult.values().stream()
                .filter(result -> result == WIN)
                .count();
    }
}
