package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.result.MatchResult;

public class Player extends Participant {

    private Money bettingMoney = Money.ZERO;

    public Player(Name name, Hand cardHand) {
        super(name, cardHand);
    }

    public Player(Name name, Hand cardHand, int bettingMoney) {
        super(name, cardHand);
        this.bettingMoney = new Money(bettingMoney);
    }

    public static Player from(String name) {
        return new Player(new Name(name), Hand.createEmptyHand());
    }

    public void setBettingMoney(Money bettingMoney) {
        this.bettingMoney = bettingMoney;
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
