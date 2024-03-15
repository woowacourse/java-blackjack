package blackjack.domain;

public class Judge {

    private final Dealer dealer;
    private final Player player;

    public Judge(final Dealer dealer, final Player player) {
        this.dealer = dealer;
        this.player = player;
    }

    public PlayerOutcome calculatePlayerOutcome() {
        if (dealer.isBlackjack() || player.isBlackjack()) {
            return calculateBlackjackCase();
        }
        if (dealer.isBust() || player.isBust()) {
            return calculateBustCase();
        }
        return calculateNormalCase();
    }

    private PlayerOutcome calculateBlackjackCase() {
        if (dealer.isBlackjack() && player.isBlackjack()) {
            return PlayerOutcome.PUSH;
        }
        if (dealer.isBlackjack()) {
            return PlayerOutcome.LOSE;
        }
        return PlayerOutcome.BLACKJACK_WIN;
    }

    private PlayerOutcome calculateBustCase() {
        if (player.isBust()) {
            return PlayerOutcome.LOSE;
        }
        return PlayerOutcome.NORMAL_WIN;
    }

    private PlayerOutcome calculateNormalCase() {
        final int dealerScore = dealer.calculateScore();
        final int playerScore = player.calculateScore();

        if (dealerScore < playerScore) {
            return PlayerOutcome.NORMAL_WIN;
        }
        if (dealerScore > playerScore) {
            return PlayerOutcome.LOSE;
        }
        return PlayerOutcome.PUSH;
    }
}
