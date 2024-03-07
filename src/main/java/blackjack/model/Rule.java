package blackjack.model;

public class Rule {
    private final Dealer dealer;

    public Rule(final Dealer dealer) {
        this.dealer = dealer;
    }

    public Result calculateResult(final Player player) {
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
}
