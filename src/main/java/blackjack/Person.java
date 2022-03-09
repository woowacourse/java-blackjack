package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private List<Card> myCards;

    public Person() {
        this.myCards = new ArrayList<>();
    }

    public void addCard(Card card) {
        myCards.add(card);
    }

    public List<Card> getMyCards() {
        return myCards;
    }

    public int score() {
        return myCards.stream()
                .mapToInt(Card::getNumber)
                .sum();
    }
}
