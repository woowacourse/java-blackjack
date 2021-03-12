package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.result.MatchResult;

public class Player extends Participant {

    private Money bettingMoney = Money.ZERO;

    public Player(Name name, Hand cardHand) {
        super(name, cardHand);
    }

    public Player(Name name, Hand cardHand, Money bettingMoney) {
        super(name, cardHand);
        this.bettingMoney = bettingMoney;
    }

    public static Player from(Name name, Money bettingMoney) {
        return new Player(name, Hand.createEmptyHand(), bettingMoney);
    }

    public void receiveCard(Card card) {
        cardHand.add(card);
    }

    public Money getWinningMoney(Dealer dealer) {
        if (blackjackWin(dealer)) {
            return bettingMoney.multiplyByRate(MatchResult.BLACKJACK.getProfitRate());
        }
        if (lose(dealer)) {
            return bettingMoney.toNegative();
        }
        if (tie(dealer)) {
            return Money.ZERO;
        }
        return bettingMoney.multiplyByRate(MatchResult.STAY.getProfitRate());
    }

    private boolean blackjackWin(Dealer dealer) {
        return isBlackjack() && !dealer.isBlackjack();
    }

    private boolean lose(Dealer dealer) {
        return isBust()
                || (dealer.isBlackjack() && !isBlackjack())
                || (getScore() < dealer.getScore());
    }

    private boolean tie(Dealer dealer) {
        return (isBlackjack() && dealer.isBlackjack())
                || (dealer.getScore() == getScore());
    }
}
