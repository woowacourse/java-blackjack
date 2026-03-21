package domain.game;

import domain.card.Card;
import java.util.List;

public interface HandState {

    HandState draw(Card card);

    HandState stay();

    boolean isFinished();

    List<Card> cards();

    int score();

    Outcome calculateOutcome(HandState dealerState);

    Outcome outcomeForBlackjack();

    Outcome outcomeForStay(int playerScore);
}
