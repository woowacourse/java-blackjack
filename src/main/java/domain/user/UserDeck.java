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
        return addSumByAce(sum);
    }

    private int addSumByAce(int sum) {
        if (sum < 12 && hasAce()) {
            sum += 10;
        }
        return sum;
    }

    public boolean hasAce() {
        return userDeck.stream()
                .anyMatch(card -> card.number() == ACE);
    }
}
