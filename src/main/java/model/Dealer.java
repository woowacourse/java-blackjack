package model;

import java.util.List;
import model.cardbehavior.CardsBehavior;
import model.cardbehavior.FirstCardsBehavior;

public class Dealer extends Participator {
    private static final String DEALER_NAME = "딜러";

    private CardsBehavior behavior;

    public Dealer() {
        super(new PlayerName(DEALER_NAME));
        behavior = new FirstCardsBehavior();
    }

    public void setBehavior(CardsBehavior behavior) {
        this.behavior = behavior;
    }

    @Override
    public List<Card> getCards() {
        return behavior.getCards(cards);
    }

    @Override
    public boolean canReceiveCard() {
        return cards.canReceiveCardForDealer();
    }

    public Result matchWith(Participator participator) {
        Result result = this.cards.getResult(participator.cards);
        if (result.equals(Result.DRAW) && this.cards.isBusted()) {
            return Result.WIN;
        }
        return result;
    }
}
