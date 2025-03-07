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
    public boolean canHit() {
        Set<Integer> possibleSum = cardDeck.calculatePossibleSum();
        int minScore = Collections.min(possibleSum);
        return minScore <= TARGET_SCORE;
    }

    @Override
    public String getName() {
        return name;
    }
}
