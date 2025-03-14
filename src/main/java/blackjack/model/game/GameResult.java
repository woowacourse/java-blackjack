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

    public static Map<Player, PlayerResult> calculateGameResult(Dealer dealer, Players players) {
        Map<Player, PlayerResult> winLoseResult = new HashMap<>();
        players.getParticipants().forEach(
                participant -> {
                    winLoseResult.put(participant, getParticipantResult(dealer, participant));
                }
        );
        return winLoseResult;
    }

    private static PlayerResult getParticipantResult(Dealer dealer, Player player) {
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


}
