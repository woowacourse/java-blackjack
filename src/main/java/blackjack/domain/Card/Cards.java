package blackjack.domain.Card;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Cards {
    public static final int MAX_SUM = 21;
    private Set<Card> cards = new HashSet<>();

    public void add(Card card) {
        this.cards.add(card);
    }

    public int getSum() {
        int sum =  cards.stream()
                .mapToInt(Card::getNumber)
                .sum();

        return handleAce(sum);
    }

    private int handleAce(int sum) {
        for (Card card : cards) {
            if (sum <= MAX_SUM) {
                break;
            }

            if (card.has(CardNumber.ACE)) {
                sum -= 10;
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
