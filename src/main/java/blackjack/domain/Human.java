package blackjack.domain;

import java.util.List;

public abstract class Human {

    abstract List<String> showCards();

    abstract int showSumOfCards();

    abstract void receiveInitCards(List<Card> initCards);

    abstract void receiveCard(Card card);

    abstract boolean isBust();

    abstract boolean isReceived();
}
