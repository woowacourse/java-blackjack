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
        if (getHand().isBust()) {
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
        if (getHand().isBust()) {
            return ResultType.LOSE;
        }
        int myScore = getScore();
        int dealerScore = dealer.getScore();

        return ResultType.getResultType(myScore - dealerScore);
    }


}
