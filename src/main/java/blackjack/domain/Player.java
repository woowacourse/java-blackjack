package blackjack.domain;

import java.util.Collections;
import java.util.Set;

public class Player extends Participant {
    private final String name;

    public Player(String name, CardDeck cardDeck, CardDump cardDump) {
        super(cardDeck, cardDump);
        this.name = name;
    }

    @Override
    public boolean canTakeExtraCard() {
        Set<Integer> possibleSum = cardDeck.calculatePossibleSum();
        int minScore = Collections.min(possibleSum);

        return minScore <= 21;
    }

    @Override
    public int calculateTotalCardScore() {
        Set<Integer> possibleSum = cardDeck.calculatePossibleSum();
        return possibleSum.stream()
                .filter(sum -> sum <= 21)
                .max(Integer::compareTo)
                .orElse(Collections.min(possibleSum));
    }

    @Override
    public String getName() {
        return name;
    }
}
