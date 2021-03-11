package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public class Player extends Participant {

    public Player(final Cards cards, final String name) {
        super(cards, name);
    }

    public boolean playerBlackJack(final Dealer dealer) {
        return this.isBlackJack() && !dealer.isBlackJack();
    }

    public boolean dealerBlackJack(final Dealer dealer) {
        return !this.isBlackJack() && dealer.isBlackJack();
    }

    public boolean bothBlackJack(final Dealer dealer) {
        return this.isBlackJack() && dealer.isBlackJack();
    }

    public boolean winCondition(final Dealer dealer) {
        return dealer.isBust() && !this.isBust() ||
                !this.isBust() && this.scoreBiggerThan(dealer);
    }

    public boolean lossCondition(final Dealer dealer) {
        return this.isBust() ||
                (!dealer.isBust() && this.scoreSmallerThan(dealer));
    }

    public boolean drawCondition(final Dealer dealer) {
        return this.getScore() == dealer.getScore();
    }

    public boolean scoreBiggerThan(final Dealer dealer) {
        return this.getScore() > dealer.getScore();
    }

    public boolean scoreSmallerThan(final Dealer dealer) {
        return this.getScore() < dealer.getScore();
    }

    @Override
    public List<Card> getInitCardsAsList() {
        return hand.getCardsAsList();
    }
}
