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

    public int calculateBettingProfit(MatchCase matchCase) {
        if(WIN.equals(matchCase) && !isBlackjack()) {
            return bettingAmount.getPrice();
        }
        if(WIN.equals(matchCase) && isBlackjack()) {
            return (int) (bettingAmount.getPrice() * BLACKJACK_MULTIPLIER);
        }

        if(LOSE.equals(matchCase)) {
            return bettingAmount.getPrice() * -1;
        }

        return 0; // 무승부인 경우
    }
}
