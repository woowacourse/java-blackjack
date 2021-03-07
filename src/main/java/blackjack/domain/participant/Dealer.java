package blackjack.domain.participant;

public class Dealer extends Participant {
    private static final int MAX_SUM_FOR_MORE_CARD = 16;
    private static final String FIXED_DEALER_NAME = "딜러";

    public Dealer() {
        super(FIXED_DEALER_NAME);
    }

    public boolean checkMoreCardAvailable() {
        return (hand.calculateScore() <= MAX_SUM_FOR_MORE_CARD);
    }

    public String checkResult(final Player player) {
        if (player.isBust()) {
            return "승";
        }
        if (this.isBust()) {
            return "패";
        }
        final int dealerScore = this.hand.calculateScore();
        final int playerScore = player.hand.calculateScore();
        if (dealerScore > playerScore) {
            return "승";
        } else if (dealerScore == playerScore) {
            return "무";
        } else {
            return "패";
        }
    }
}
