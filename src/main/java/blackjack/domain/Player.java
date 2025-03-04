package blackjack.domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Player {
    private final String name;
    private final CardDeck cardDeck;
    private final CardGenerator cardGenerator;

    public Player(String name, CardDeck cardDeck, CardGenerator cardGenerator) {
        this.name = name;
        this.cardDeck = cardDeck;
        this.cardGenerator = cardGenerator;
    }


    public boolean canTakeExtraCard() {
        Set<Integer> possibleSum = cardDeck.calculatePossibleSum();
        int minScore = Collections.min(possibleSum);

        return minScore <= 21;
    }

    public int calculateTotalCardScore() {
        Set<Integer> possibleSum = cardDeck.calculatePossibleSum();
        if (isBust()) {
            return Collections.min(possibleSum);
        }
        return Collections.max(possibleSum);
    }

    public boolean isBust() {
        Set<Integer> possibleSum = cardDeck.calculatePossibleSum();
        int totalScore = Collections.max(possibleSum);
        return totalScore > 21;
    }

    public void addCard() {
        Card card = cardGenerator.generate();
        cardDeck.add(card);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCardDeck() {
        return cardDeck.getCards();
    }
}
