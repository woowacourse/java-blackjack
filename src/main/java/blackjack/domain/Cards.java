package blackjack.domain;

import static blackjack.domain.BlackjackGame.BLACKJACK_NUMBER;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards() {
        this(new ArrayList<>());
    }

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int getTotalNumber() {
        boolean containAce = cards.stream()
                .anyMatch(Card::isAce);

        int sum = cards.stream()
                .mapToInt(Card::getNumberValue)
                .sum();

        return optimizeTotalNumber(containAce, sum);
    }

    private int optimizeTotalNumber(boolean containAce, int totalNumber) {
        int totalNumberInAce11 = totalNumber + 10;

        if (containAce && totalNumberInAce11 <= BLACKJACK_NUMBER) {
            return totalNumberInAce11;
        }
        return totalNumber;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && getTotalNumber() == BLACKJACK_NUMBER;
    }
}
