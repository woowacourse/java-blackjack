package blackjack.domain.card;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Cards {
    public static final int MAX_SUM = 21;
    private static final int ACE_VALUE_DIFFERENCE = 10;

    private Set<Card> cards = new HashSet<>();

    public void add(Card card) {
        this.cards.add(card);
    }

    public int computeScore() {
        int sum = cards.stream()
                .mapToInt(Card::getCardValue)
                .sum();

        return handleAce(sum);
    }

    private int handleAce(int sum) {
        for (Card card : cards) {
            if (sum <= MAX_SUM) {
                break;
            }

            if (card.isAce()) {
                sum -= ACE_VALUE_DIFFERENCE;
            }
        }
        return sum;
    }

    public List<String> showCardsInfo() {
        return cards.stream()
                .map(Card::showCardInfo)
                .collect(Collectors.toList());
    }

    public Set<Card> getCards() {
        return cards;
    }
}
