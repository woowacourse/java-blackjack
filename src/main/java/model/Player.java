package model;

import static model.card.Cards.BLACK_JACK_SCORE;

import model.cardGettable.EveryCardsGettable;

public class Player extends Participator {

    public Player(String playerName) {
        super(playerName);
        this.cardsGettableStrategy = new EveryCardsGettable();
    }

    @Override
    public boolean canReceiveCard() {
        return cards.getSum() < BLACK_JACK_SCORE;
    }

    public Result matchWith(Dealer dealer) {
        if (dealer.cards.isBusted() && this.cards.isBusted()) {
            return Result.LOSE;
        }
        return this.cards.getResult(dealer.cards);
    }


}
