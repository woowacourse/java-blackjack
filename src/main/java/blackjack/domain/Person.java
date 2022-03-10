package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private List<Card> myCards;

    public Person(String name) {
        this.name = name;
        this.myCards = new ArrayList<>();
    }

    public void addCard(Card card) {
        myCards.add(card);
    }

    public List<Card> getMyCards() {
        return myCards;
    }

    public String getName() {
        return name;
    }

    public int score() {
        return myCards.stream()
                .mapToInt(Card::getNumber)
                .sum();
    }
}
