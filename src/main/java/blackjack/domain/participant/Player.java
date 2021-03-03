package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.Response;
import blackjack.domain.ResultType;
import java.util.Objects;

public class Player extends BlackJackParticipant{

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

    public void willContinue(String input) {
        if (!Response.getHitStatus(input)) {
            cannotDraw();
        }
    }

    public ResultType match(Dealer dealer) {
        int myScore = getScore();
        int dealerScore = dealer.getScore();

        return ResultType.getResultType(myScore - dealerScore);
    }


}
