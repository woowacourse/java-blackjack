package blackjack.domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Player {
    private final String name;
    private final CardDeck cardDeck;
    private final CardDump cardDump;

    public Player(String name, CardDeck cardDeck, CardDump cardDump) {
        this.name = name;
        this.cardDeck = cardDeck;
        this.cardDump = cardDump;
    }

    public boolean canTakeExtraCard() {
        Set<Integer> possibleSum = cardDeck.calculatePossibleSum();
        int minScore = Collections.min(possibleSum);

        return minScore <= 21;
    }

    public int calculateTotalCardScore() {
        Set<Integer> possibleSum = cardDeck.calculatePossibleSum();
        return possibleSum.stream()
                .filter(sum -> sum <= 21)
                .max(Integer::compareTo)
                .orElse(Collections.min(possibleSum));
    }

    public boolean isBust() {
        Set<Integer> possibleSum = cardDeck.calculatePossibleSum();
        int totalScore = Collections.max(possibleSum);
        return totalScore > 21;
    }

    public void addCard() {
        Card card = cardDump.drawCard();
        cardDeck.add(card);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCardDeck() {
        return cardDeck.getCards();
    }
}
