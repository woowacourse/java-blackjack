package domain.state;

import domain.card.Card;
import domain.card.Score;

import java.util.List;

public interface State {

    State draw(Card card);

    State stay();

    Score score();

    double profit(int base);

    List<Card> cards();

    boolean isHit();

    boolean isRunning();

    boolean isStay();

    int handSize();
}
