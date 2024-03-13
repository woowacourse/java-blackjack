package blackjack.model.state;

import blackjack.model.Money;
import blackjack.model.deck.Card;
import java.util.List;

public interface State {
    State draw(Card card);

    State stand();

    List<Card> getCards();

    int calculateScore();

    double calculateProfit(Money money);
}
