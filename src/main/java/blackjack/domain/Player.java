package blackjack.domain;

import java.util.Collections;
import java.util.Set;

public class Player extends Participant {
    private static final int TARGET_SCORE = 21;

    private final String name;

    public Player(String name, CardDeck cardDeck, CardDump cardDump) {
        super(cardDeck, cardDump);
        this.name = name;
    }

    @Override
    public boolean canHit() {
        Set<Integer> possibleSum = cardDeck.calculatePossibleSum();
        int minScore = Collections.min(possibleSum);
        return minScore <= TARGET_SCORE;
    }

    @Override
    public int calculateScore() {
        Set<Integer> possibleSum = cardDeck.calculatePossibleSum();
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
