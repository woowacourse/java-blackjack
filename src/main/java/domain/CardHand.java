package domain;

import java.util.ArrayList;
import java.util.List;

public class CardHand {
    private static final int UPPER_LIMIT_TO_ADD = 11;
    private static final int ACE_BONUS = 10;
    private final List<Card> cards;

    public CardHand() {
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

    public boolean haveAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public boolean haveTenScoreCard() {
        return cards.stream().anyMatch(Card::isTenScoreCard);
    }

    public List<Card> getCards() {
        return cards;
    }
}
