package domain;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

import static domain.card.Rank.ACE;

public class Hand {
    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public int getSum() {
        int sum = getInitSum();
        long aceCount = countAce();

        while (aceCount > 0 && sum > 21) {
            sum -= 10;
            aceCount--;
        }

        return sum;
    }

    private int getInitSum() {
        return cards.stream()
                .mapToInt(c -> c.rank().getScore())
                .sum();
    }

    private long countAce() {
        return cards.stream()
                .filter(c -> c.rank() == ACE)
                .count();
    }

    public boolean isBurst() {
        return getSum() > 21;
    }


    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
