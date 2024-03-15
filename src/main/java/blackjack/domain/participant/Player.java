package blackjack.domain.participant;

import blackjack.domain.Hand;
import blackjack.domain.card.Card;

public class Player extends Gamer {

    private final Name name;
    protected final Betting betting;

    public Player(final Name name, Betting betting) {
        super();

        this.name = name;
        this.betting = betting;
    }


    public void draw(final Card card) {
        hand.add(card);
    }

    @Override
    public boolean canReceiveCard() {
        return hand.calculateScore() < Hand.BLACKJACK_BOUND;
    }

    public void win(final Profit dealerProfit) {
        if (hand.isBlackjack()) {
            dealerProfit.lose(betting.getDealerBlackjackAmount());
            profit.earnBlackjack(betting.getBlackjackAmount(), betting.getAmount());
            return;
        }
        dealerProfit.lose(betting.getAmount());
        profit.earn(betting.getWinAmount(), betting.getAmount());
    }

    public void lose(final Profit dealerProfit) {
        dealerProfit.earn(betting.getAmount());
        profit.lose(betting.getAmount());
    }

    public String getName() {
        return name.name();
    }

    @Override
    public String getProfit() {
        return profit.toString();
    }
}
