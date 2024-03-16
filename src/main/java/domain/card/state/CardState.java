package domain.card.state;

import domain.card.Card;
import domain.card.Cards;

public interface CardState {
    CardState receive(Card card);

    CardState finish();

    boolean isFinished();

    int calculateProfit(int bettingAmount);

    Cards cards();
}
