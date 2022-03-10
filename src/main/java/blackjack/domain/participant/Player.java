package blackjack.domain.participant;

import blackjack.domain.Result;
import blackjack.domain.card.Card;

public class Player extends Participant {

    public Player(String name) {
        this.name = new Name(name);
    }

    public void receiveCard(Card card) {
        cardHand.add(card);
    }

    public Result compareMatchResult(Dealer dealer) {
        return cardHand.compareMatchResult(dealer.getCardHand());
    }


}
