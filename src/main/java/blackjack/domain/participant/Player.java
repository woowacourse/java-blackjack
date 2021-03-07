package blackjack.domain.participant;

public class Player extends Participant {
    public Player(final String name) {
        super(name);
    }

    public String checkResult(final Dealer dealer) {
        if (this.isBust()) {
            return "패";
        }
        if (dealer.isBust()) {
            return "승";
        }
        final int playerScore = this.hand.calculateScore();
        final int dealerScore = dealer.hand.calculateScore();
        if (playerScore > dealerScore) {
            return "승";
        } else if (playerScore == dealerScore) {
            return "무";
        } else {
            return "패";
        }
    }
}
