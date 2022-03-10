package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Person {
    protected String name;
    protected List<Card> myCards;

    public Person(String name) {
        this.name = name;
        this.myCards = new ArrayList<>();
    }

    public void addCard(Card card) {
        myCards.add(card);
    }

    public int score() {
        return myCards.stream()
                .mapToInt(Card::getNumber)
                .sum();
    }

    public List<Card> getMyCards() {
        return myCards;
    }

    public String getName() {
        return name;
    }
}
