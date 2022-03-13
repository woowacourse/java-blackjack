package model;

import model.cardGettable.EveryCardsGettable;

public class Player extends Participator {

    public Player(String playerName) {
        super(playerName);
        this.cardsGettableStrategy = new EveryCardsGettable();
    }

    @Override
    public boolean canReceiveCard() {
        return cards.canReceiveCard();
    }

    public Result matchWith(Dealer dealer) {
        if (dealer.cards.isBusted() && this.cards.isBusted()) {
            return Result.LOSE;
        }
        return this.cards.getResult(dealer.cards);
    }
}
