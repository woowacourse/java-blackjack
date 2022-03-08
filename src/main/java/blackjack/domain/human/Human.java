package blackjack.domain.human;

import blackjack.domain.Card;
import java.util.List;

public abstract class Human {

    public abstract String getName();

    public abstract void addCard(Card card);

    public abstract List<Card> getCards();

    public int getPoint() {
        int point = 0;
        for (Card card : getCards()) {
            point += card.getNumber();
        }
        return point;
    }
}
