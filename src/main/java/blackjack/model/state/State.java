package blackjack.model.state;

import blackjack.model.Money;
import blackjack.model.deck.Card;
import blackjack.model.participant.Hand;

public interface State {
    State draw(Card card);

    State stand();

    Hand hand();

    int calculateScore();

    double calculateProfit(Money money);
}
