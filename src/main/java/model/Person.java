package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.card.Card;

public class Person {

    private List<Card> cards;

    public Person() {
        this.cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int sumCardNumbers() {
        int sum = 0;
        for(Card card : cards) {
            sum += card.minimumNumber();
        }

        List<Integer> addNumbers = cards.stream()
                .map(Card::subtractMaxMinNumber)
                .filter(subtractNumber -> subtractNumber != 0)
                .toList();

        return sumAce(sum, addNumbers);
    }

    private int sumAce(int sum, List<Integer> addNumbers) {
        for (int number : addNumbers) {
            if (sum + number <= 21) {
                sum += number;
            }
        }
        return sum;
    }
}
