package domain.game;

import domain.card.Card;
import java.util.List;

public interface HandState {

    Outcome versus(HandState other);

    boolean canHit();

    HandState draw(Card card);

    HandState stay();

    List<Card> cards();

    int score();
}
