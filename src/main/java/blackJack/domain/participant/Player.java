package blackJack.domain.participant;

import blackJack.domain.result.BettingAmount;
import blackJack.domain.result.BlackJackMatch;

public class Player extends Participant {

    private BettingAmount bettingAmount;

    public Player(String name) {
        super(name);
        bettingAmount = BettingAmount.newByDefault();
    }

    @Override
    public boolean canHit() {
        return !this.getScore().isBurst();
    }

    public void betting(int bettingAmount) {
        this.bettingAmount = this.bettingAmount.startBetting(bettingAmount);
    }

    public int calculateProfit(BlackJackMatch blackJackMatch) {
        return bettingAmount.calculateProfit(blackJackMatch);
    }

    public BlackJackMatch calculateMatchResult(Participant dealer) {
        if (this.isBurst()) {
            return BlackJackMatch.LOSE;
        }
        if (this.isBlackJack() && !dealer.isBlackJack()) {
            return BlackJackMatch.BLACK_JACK_WIN;
        }
        if (this.getScore().compareTo(dealer.getScore()) == 0) {
            return getResultAtSameScore(dealer);
        }
        return getResultAtDifferentScore(dealer);
    }

    private BlackJackMatch getResultAtSameScore(Participant dealer) {
        if (!this.isBlackJack() && dealer.isBlackJack()) {
            return BlackJackMatch.LOSE;
        }
        return BlackJackMatch.DRAW;
    }

    private BlackJackMatch getResultAtDifferentScore(Participant dealer) {
        if (dealer.isBurst()) {
            return BlackJackMatch.WIN;
        }
        if (this.getScore().compareTo(dealer.getScore()) > 0) {
            return BlackJackMatch.WIN;
        }
        return BlackJackMatch.LOSE;
    }
}
