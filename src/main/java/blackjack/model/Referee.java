package blackjack.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Referee {
    private final Dealer dealer;
    private final Players players;

    public Referee(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Map<String, Result> judgePlayerWinner() {
        Map<String, Result> result = new LinkedHashMap<>();
        for (Player player: players.getPlayers()) {
            result.put(player.getName(), calculateResult(player));
        }
        return result;
    }

    private Result calculateResult(final Player player) {
        int playerScore = player.notifyScore();
        int dealerScore = dealer.notifyScore();
        int playerCardCount = player.announceCardCount();
        int dealerCardCount = dealer.announceCardCount();

        if (playerScore > dealerScore) {
            return Result.WIN;
        }

        if (playerScore == dealerScore && playerCardCount < dealerCardCount) {
            return Result.WIN;
        }

        if (player.announceBlackJack() && !dealer.announceBlackJack()) {
            return Result.WIN;
        }

        if (!dealer.isNotBust() && player.isNotBust()) {
            return Result.WIN;
        }

        return Result.LOSE;
    }
}
