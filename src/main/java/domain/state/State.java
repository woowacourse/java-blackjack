package domain.state;

import domain.card.Card;
import domain.game.Score;
import domain.user.Hand;

import java.util.List;

public interface State {

    State draw(Card card);

    State stay();

    Score score();

    Hand hand();

    double profit(int base);

    List<Card> cards();
}
