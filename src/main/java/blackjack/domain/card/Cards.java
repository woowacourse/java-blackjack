package blackjack.domain.card;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Cards {
    public static final int UPPER_LIMIT = 21;
    private Set<Card> cards = new HashSet<>();

    public void add(Card card) {
        this.cards.add(card);
    }

    public int computeSum() {
        int sum = cards.stream()
                .mapToInt(Card::getNumber)
                .sum();

        return handleAce(sum);
    }

    private int handleAce(int sum) {
        int aceCount = (int) cards.stream()
                .filter(x -> x.has(CardNumber.ACE))
                .count();

        while (aceCount-- > 0) {
            if (UPPER_LIMIT < sum) {
                sum -= CardNumber.ACE_DIFF;
            }
        }
        return sum;
    }

    public List<String> getInfo() {
        return cards.stream()
                .map(Card::getInfo)
                .collect(Collectors.toList());
    }

    public Set<Card> getCards() {
        return cards;
    }
}
