package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public class Referee {
    private static final Referee INSTANCE = new Referee();

    private Referee() {
    }

    public static Referee getInstance() {
        return INSTANCE;
    }

    public HandResult getPlayerResult(Player player, Dealer dealer) {
        if (dealer.isBust()) {
            return HandResult.WIN;
        }
        if (player.isBust()) {
            return HandResult.LOSE;
        }
        return getPlayerResultWithoutBust(player, dealer);
    }

    private HandResult getPlayerResultWithoutBust(Player player, Dealer dealer) {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();
        if (playerScore == dealerScore) {
            return HandResult.DRAW;
        }
        if (playerScore < dealerScore) {
            return HandResult.LOSE;
        }
        return HandResult.WIN;
    }
}
