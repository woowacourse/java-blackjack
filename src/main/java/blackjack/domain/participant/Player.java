package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.domain.Response;
import blackjack.domain.ResultType;

public class Player extends BlackJackParticipant {

    public Player(String name) {
        super(name);
    }

    public Player(String name, Hand hand) {
        super(name, hand);
    }

    @Override
    public void draw(Deck deck) {
        getHand().addCard(deck.draw());
        if (isBust()) {
            cannotDraw();
        }
    }

    public boolean willContinue(String input) {
        if (!Response.getHitStatus(input)) {
            cannotDraw();
            return false;
        }
        return true;
    }

    public ResultType match(Dealer dealer) {
        if (isBust()) {
            return ResultType.LOSE;
        }
        if (dealer.isBust()) {
            return ResultType.WIN;
        }
        return ResultType.getResultType(getScore() - dealer.getScore());
    }
}
