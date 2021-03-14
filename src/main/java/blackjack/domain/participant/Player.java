package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.Result;

import java.util.List;

public class Player extends Participant {

    private Betting betting;
    private Profit profit;

    public Player(final Cards cards, final String name, final Double betting, final Double profit) {
        super(cards, name);
        this.betting = new Betting(betting);
        this.profit = new Profit(profit);
    }

    public Player(final Cards cards, final String name, final Double betting) {
        super(cards, name);
        this.betting = new Betting(betting);
    }

    public Player(final Cards cards, final String name) {
        super(cards, name);
    }

    public Player changeBetting(Double betting) {
        return new Player(this.hand, this.getNameAsString(), betting);
    }

    public double getBetting() {
        return betting.getBetting();
    }

    public Player changeProfit(Result result) {
        return new Player(this.hand, this.getNameAsString(), betting.getBetting(),
                betting.getBetting() * result.getRate());
    }

    public double getProfit() {
        return profit.getProfit();
    }

    public boolean blackJackWinCondition(final Dealer dealer) {
        return this.isBlackJack() && !dealer.isBlackJack();
    }

    public boolean winCondition(final Dealer dealer) {
        return dealer.isBust() && !this.isBust() ||
                !this.isBust() && this.scoreBiggerThan(dealer);
    }

    public boolean lossCondition(final Dealer dealer) {
        return (!this.isBlackJack() && dealer.isBlackJack()) ||
                (this.isBust() || (!dealer.isBust() && this.scoreSmallerThan(dealer)));
    }

    public boolean drawCondition(final Dealer dealer) {
        return (this.isBlackJack() && dealer.isBlackJack()) ||
                (this.getScore() == dealer.getScore());
    }

    private boolean scoreBiggerThan(final Dealer dealer) {
        return this.getScore() > dealer.getScore();
    }

    private boolean scoreSmallerThan(final Dealer dealer) {
        return this.getScore() < dealer.getScore();
    }

    @Override
    public List<Card> getInitCardsAsList() {
        return hand.getCardsAsList();
    }
}
