package blackjack.model.state;

import blackjack.model.card.HandCard;
import blackjack.model.card.CardDeck;

public class DrawState extends State {
    public DrawState(HandCard handCard) {
        super(handCard);
    }

    @Override
    public State draw(CardDeck cardDeck) {
        handCard.add(cardDeck.pick());
        if(handCard.score().smallScore() > BLACKJACK_NUMBER && handCard.score().bigScore() > BLACKJACK_NUMBER){
            return new BustState(handCard);
        }
        return this;
    }

    public State turnStandState(){
        return new StandState(handCard);
    }

    public State turnDealerDrawState(){
        return new DealerDrawState(handCard);
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
