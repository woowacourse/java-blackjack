package domain.card;

import java.util.ArrayList;
import java.util.List;

public final class Hand {
    private static final int UPPER_LIMIT_TO_ADD = 11;
    private static final int ACE_BONUS = 10;
    private static final int INITIAL_COUNT = 2;
    private final List<Card> cards;

    public Hand(){
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int scoreBeforeCheckAce = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        if (haveAce() && scoreBeforeCheckAce <= UPPER_LIMIT_TO_ADD) {
            return scoreBeforeCheckAce + ACE_BONUS;
        }
        return scoreBeforeCheckAce;
    }

    private boolean haveAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean isInitialState() {
        return this.cards.size() == INITIAL_COUNT;
    }
}
