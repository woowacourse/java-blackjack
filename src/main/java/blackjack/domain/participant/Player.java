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

    public boolean isStay() {
        return isStay;
    }

    public MatchResult match(Dealer dealer) {
        if (isDraw(dealer)) {
            return MatchResult.DRAW;
        }
        if (isWin(dealer)) {
            return MatchResult.WIN;
        }
        return MatchResult.LOSE;
    }

    private boolean isDraw(Dealer dealer) {
        return this.cards.isBust() && dealer.cards.isBust() || this.cards.sum() == dealer.cards.sum();
    }

    private boolean isWin(Dealer dealer) {
        return !this.cards.isBust()
                && (dealer.getCards().isBust() || dealer.getCards().sum() < this.cards.sum());
    }

    @Override
    public boolean isFinished() {
        return cards.isBust() || cards.isBlackJack() || isStay();
    }

    @Override
    public String toString() {
        return "Player{" +
                "name=" + name +
                ", cards=" + cards +
                '}';
    }
}
