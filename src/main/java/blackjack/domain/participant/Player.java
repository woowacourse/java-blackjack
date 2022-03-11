package blackjack.domain.participant;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import java.util.Map;

public class Player extends Participant {

    private Result result;

    public Player(String name) {
        this.name = new Name(name);
    }

    public void receiveCard(Card card) {
        cardHand.add(card);
    }

    public void decideMatchResult(Dealer dealer) {
        result =  cardHand.compareMatchResult(dealer.getCardHand());
    }

    public Result getResult() {
        return result;
    }
}
