package blackjack.domain;

import java.util.List;

public abstract class Human {

    abstract List<String> getCards();

    abstract int getTotal();

    abstract void receiveInitCard(List<Card> initCards);

    abstract void receiveCard(Card card);

    abstract boolean isBust();

    abstract boolean isReceived();
}
