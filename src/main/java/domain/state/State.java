package domain.state;

import domain.card.Card;
import domain.user.Hand;

import java.util.List;

public interface State {

    State draw(Card card);

    State stay();

    Hand hand();

    List<Card> cards();
}
