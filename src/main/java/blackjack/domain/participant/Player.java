package blackjack.domain.participant;

import blackjack.domain.card.Cards;
import blackjack.domain.game.MatchResult;

public class Player extends Participant {

    private boolean isStay = false;

    public Player(Name name, Cards cards) {
        super(name, cards);
    }

    public void stay() {
        isStay = true;
    }

    public MatchResult match(Dealer dealer) {
        if (isWin(dealer)) {
            return MatchResult.WIN;
        }
        if (isLose(dealer)) {
            return MatchResult.LOSE;
        }
        return MatchResult.DRAW;
    }

    private boolean isWin(Dealer dealer) {
        return (this.cards.isBlackJack() && !dealer.cards.isBlackJack())
                || (!this.cards.isBust() && (dealer.cards.isBust() || dealer.cards.sum() < this.cards.sum()));
    }

    private boolean isLose(Dealer dealer) {
        return (dealer.cards.isBlackJack() && !this.cards.isBlackJack())
                || (!dealer.cards.isBust() && (this.cards.isBust() || dealer.cards.sum() > this.cards.sum()));
    }

    @Override
    public boolean isFinished() {
        return cards.isBust() || cards.isBlackJack() || isStay;
    }

    public boolean isStay() {
        return isStay;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name=" + name +
                ", cards=" + cards +
                '}';
    }
}
