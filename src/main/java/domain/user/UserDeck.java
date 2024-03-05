package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static domain.card.Number.ACE;

public class UserDeck {
    private final List<Card> userDeck = new ArrayList<>();

    public void pushCard(Card card) {
        userDeck.add(card);
    }

    public List<Card> getUserDeck() {
        return Collections.unmodifiableList(userDeck);
    }

    public int sumCard() {
        int sum = userDeck.stream()
                .mapToInt(Card::getNumberValue)
                .sum();

        return reduceSumByAce(sum);
    }

    private int reduceSumByAce(int sum) {
        int aceCount = aceCount();
        while (sum > 22 && aceCount > 0) {
            sum -= 10;
            aceCount--;
        }
        return sum;
    }

    public boolean hasAce() {
        return userDeck.stream()
                .anyMatch(card -> card.number() == ACE);
    }

    private int aceCount() {
        return (int) userDeck.stream()
                .filter(card -> card.number() == ACE)
                .count();
    }
}
