package blackjack.model.player;

import static blackjack.model.game.PlayerResult.BLACKJACK;
import static blackjack.model.game.PlayerResult.DRAW;
import static blackjack.model.game.PlayerResult.LOSE;
import static blackjack.model.game.PlayerResult.WIN;

import blackjack.model.game.PlayerResult;
import java.util.HashMap;
import java.util.Map;

public class Dealer extends Participant {

    private static final int BLACKJACK_POINT = 21;
    private static final int BLACKJACK_SIZE = 2;

    public Map<Player, PlayerResult> calculateGameResult(Players players) {
        Map<Player, PlayerResult> winLoseResult = new HashMap<>();
        players.getParticipants().forEach(
                participant -> {
                    winLoseResult.put(participant, getParticipantResult(participant));
                }
        );
        return winLoseResult;
    }

    private PlayerResult getParticipantResult(Player player) {
        if (isBustResult(player)) {
            return LOSE;
        }
        if (isDealerBust()) {
            return WIN;
        }
        if (isBlackjackDraw(player)) {
            return DRAW;
        }
        if (isDealerBlackJack()) {
            return LOSE;
        }
        if (isPlayerBlackjack(player)) {
            return BLACKJACK;
        }
        return comparePoints(player);
    }

    private boolean isBustResult(Player player) {
        return player.isBust();
    }

    private boolean isDealerBust() {
        return this.isBust();
    }

    private boolean isBlackjackDraw(Player player) {
        return isPlayerBlackjack(player) && isDealerBlackJack();
    }

    private boolean isDealerBlackJack() {
        return this.calculatePoint() == BLACKJACK_POINT && this.getReceivedCards().size() == 2;
    }

    private boolean isPlayerBlackjack(Player player) {
        return player.calculatePoint() == BLACKJACK_POINT && player.getReceivedCards().size() == BLACKJACK_SIZE;
    }

    private PlayerResult comparePoints(Player player) {
        int dealerPoint = this.calculatePoint();
        int playerPoint = player.calculatePoint();
        if (dealerPoint > playerPoint) {
            return LOSE;
        }
        if (dealerPoint < playerPoint) {
            return WIN;
        }
        return DRAW;
    }
}
