package domain.user.state;

import static domain.card.Denomination.ACE;
import static domain.card.Denomination.FOUR;
import static domain.card.Denomination.JACK;
import static domain.card.Denomination.QUEEN;
import static domain.card.Denomination.SIX;
import static domain.card.Denomination.TEN;
import static domain.card.Suits.DIAMOND;

import domain.card.Card;

public class StateFixtures {

    public static State createBlackJackState() {
        return new Ready()
            .draw(Card.of(ACE, DIAMOND))
            .draw(Card.of(JACK, DIAMOND)); // Ready -> BlackJack
    }

    public static State createBustState() {
        return new Ready()
            .draw(Card.of(TEN, DIAMOND))
            .draw(Card.of(JACK, DIAMOND)) // Ready -> Running
            .draw(Card.of(QUEEN, DIAMOND)); // Running -> Terminated(Bust)
    }

    public static State createStayState() {
        return new Ready()
            .draw(Card.of(TEN, DIAMOND))
            .draw(Card.of(SIX, DIAMOND)) // Ready -> Running
            .draw(Card.of(FOUR, DIAMOND))
            .stay(); // Running -> Terminated(Stay)
    }
}
