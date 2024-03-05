package model;

import static model.card.CardNumber.ACE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.card.Card;
import model.card.CardNumber;

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
        List<Integer> addNumbers = new ArrayList<>();
        int sum = 0;
        for(Card card : cards) {
            if (card.subtractMaxMinNumber() == 0) {
                sum += card.minimumNumber();
                continue;
            }
            sum += card.minimumNumber();
            addNumbers.add(card.subtractMaxMinNumber());
        }
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
