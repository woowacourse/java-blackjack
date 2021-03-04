package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.Response;
import blackjack.domain.ResultType;

public class Player extends BlackJackParticipant {

    public Player(String name) {
        super(name);
    }

    @Override
    public void drawCard(Deck deck) {
        getHand().addCard(deck.draw());
        if (isBust()) {
            cannotDraw();
        }
    }

    public void willContinue(String input, Deck deck) {
        if (!Response.getHitStatus(input)) {
            cannotDraw();
            return;
        }
        drawCard(deck);
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
