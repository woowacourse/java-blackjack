package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.Response;
import blackjack.domain.ResultType;

public class Player extends Participant {

    public Player(String name, Deck deck) {
        super(name, deck);
    }

    @Override
    public void drawCard(Deck deck) {
        getHand().addCard(deck.draw());
        if (getHand().isBust()) {
            cannotDraw();
        }
    }

    public void willContinue(Response response, Deck deck) {
        if (!response.getHitStatus()) {
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
