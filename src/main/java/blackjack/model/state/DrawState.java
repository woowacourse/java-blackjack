package blackjack.model.state;

import blackjack.Hand;
import blackjack.model.card.CardDeck;

public class DrawState extends State {
    public DrawState(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(CardDeck cardDeck) {
        hand.add(cardDeck.pick());
        if(hand.score().smallScore() > BLACKJACK_NUMBER && hand.score().bigScore() > BLACKJACK_NUMBER){
            return new BustState(hand);
        }
        return this;
    }

    public State turnStandState(){
        return new StandState(hand);
    }

    public State turnDealerDrawState(){
        return new DealerDrawState(hand);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isStand() {
        return false;
    }
}
