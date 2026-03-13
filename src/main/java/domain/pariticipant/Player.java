package domain.pariticipant;

import domain.card.Deck;
import domain.card.Hand;
import domain.result.MatchCase;

import static constant.BlackjackConstant.BLACKJACK_MULTIPLIER;
import static domain.result.MatchCase.LOSE;
import static domain.result.MatchCase.WIN;

public class Player extends Participant {

    private final BettingAmount bettingAmount;

    public Player(Name name, Hand hand, BettingAmount bettingAmount) {
        super(name, hand);
        this.bettingAmount = bettingAmount;
    }

    public void hitCard(Deck deck) {
        this.drawCard(deck);
    }

    public long calculateBettingProfit(MatchCase matchCase) {
        if(WIN.equals(matchCase) && !isBlackjack()) {
            return bettingAmount.bettingAmount();
        }
        if(WIN.equals(matchCase) && isBlackjack()) {
            return (long) (bettingAmount.bettingAmount() * BLACKJACK_MULTIPLIER);
        }

        if(LOSE.equals(matchCase)) {
            return bettingAmount.bettingAmount() * -1;
        }

        return 0; // 무승부인 경우
    }

    public boolean isDraw(Dealer dealer) {
        return !dealer.isBust()
                && (dealer.getScore() == getScore())
                && ((isBlackjack() && dealer.isBlackjack())
                || (!isBlackjack() && !dealer.isBlackjack()));
    }

    public boolean isLose(Dealer dealer) {
        return isBust()
                || (!dealer.isBust() && dealer.getScore() > getScore())
                || (dealer.isBlackjack() && !isBlackjack());
    }
}
