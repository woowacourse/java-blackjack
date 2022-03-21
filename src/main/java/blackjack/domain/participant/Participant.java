package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    protected static final int TARGET_SCORE_FOR_BLACKJACK = 21;
    private static final int ADDITIONAL_SCORE_FOR_ACE = 10;

    protected int score = 0;
    private final String name;
    private final List<Card> cards = new ArrayList<>();

    public Participant(String name) {
        this.name = name;
    }

    public abstract boolean isHittable();

    public void addCard(Card card) {
        cards.add(card);
        score += card.getValue();
    }

    public boolean isBlackjack(int count) {
        return cards.size() == count && score == TARGET_SCORE_FOR_BLACKJACK;
    }

    public void computeAce() {
        if (isContainAce() && isBetterToGiveMoreForAce()) {
            score += ADDITIONAL_SCORE_FOR_ACE;
        }
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    private boolean isContainAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    private boolean isBetterToGiveMoreForAce() {
        return score + ADDITIONAL_SCORE_FOR_ACE <= TARGET_SCORE_FOR_BLACKJACK;
    }
}
