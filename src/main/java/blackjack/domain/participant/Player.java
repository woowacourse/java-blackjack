package blackjack.domain.participant;

import blackjack.domain.Response;
import blackjack.domain.ResultType;
import blackjack.domain.card.Card;
import blackjack.exception.InvalidNameInputException;

public class Player extends BlackJackParticipant {

    public Player(String name) {
        super(name);
    }

    @Override
    public void draw(Card card) {
        getHand().addCard(card);
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
