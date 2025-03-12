package blackjack.domain;

import java.util.Collections;
import java.util.Set;

public class Player extends Participant {
    private static final int TARGET_SCORE = 21;

    private final String name;

    public Player(String name) {
        super();
        this.name = name;
    }

    public void initCardDeck(CardDeck cardDeck) {
        for (Card card : cardDeck.getCards()) {
            this.cardStorage.add(card);
        }
    }

    @Override
    public boolean canHit() {
        Set<Integer> possibleSum = cardStorage.calculatePossibleSums();
        int minScore = Collections.min(possibleSum);
        return minScore <= TARGET_SCORE;
    }

    @Override
    public int calculateTotalCardScore() {
        Set<Integer> possibleSum = cardStorage.calculatePossibleSums();
        return possibleSum.stream()
                .filter(sum -> sum <= TARGET_SCORE)
                .max(Integer::compareTo)
                .orElse(Collections.min(possibleSum));
    }

    @Override
    public String getName() {
        return name;
    }
}
