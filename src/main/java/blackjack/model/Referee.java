package blackjack.model;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Referee {
    private final Dealer dealer;
    private final Players players;

    public Referee(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Map<String, Result> judgePlayerResult() {
        Map<String, Result> result = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            result.put(player.getName(), calculateResult(player));
        }
        return result;
    }

    private Result calculateResult(final Player player) {
        if (dealer.isBust()) {
            return judgePlayerResultWhenDealerBust(player);
        }
        return judgePlayerResultWhenDealerNotBust(player);
    }

    private Result judgePlayerResultWhenDealerBust(final Player player) {
        if (!player.isBust()) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    private Result judgePlayerResultWhenDealerNotBust(final Player player) {
        int playerCardCount = player.announceCardCount();
        int dealerCardCount = dealer.announceCardCount();
        if (player.isBust()) {
            return Result.LOSE;
        }
        if (player.notifyScore() > dealer.notifyScore()) {
            return Result.WIN;
        }
        if (playerCardCount < dealerCardCount) {
            return Result.WIN;
        }
        if (playerCardCount == dealerCardCount) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }

    public Map<Result, Integer> judgeDealerResult() {
        EnumMap<Result, Integer> dealerResult = new EnumMap<>(Result.class);
        for (Result playerResult : judgePlayerResult().values()) {
            dealerResult.merge(findOpposite(playerResult), 1, Integer::sum);
        }
        return dealerResult;
    }

    private Result findOpposite(final Result originResult) {
        if (originResult == Result.WIN) {
            return Result.LOSE;
        }
        if (originResult == Result.LOSE) {
            return Result.WIN;
        }
        return Result.DRAW;
    }
}
