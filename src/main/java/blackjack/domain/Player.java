package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Player {
    protected static final int ACE_CRITICAL_POINT = 11;
    protected static final int ACE_UPPER_POINT = 11;
    protected static final int ACE_LOWER_POINT = 1;
    protected static final int START_INDEX = 0;

    protected List<Card> cards = new ArrayList<>();
    protected String name;
    protected Status status;

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public int getCardsSize() {
        return this.cards.size();
    }

    public int calculateScore() {
        int score = calculateRawScore();
        if (hasAce() && score <= ACE_CRITICAL_POINT) {
            score += ACE_UPPER_POINT - ACE_LOWER_POINT;
        }
        return score;
    }

    private int calculateRawScore() {
        return cards.stream()
                .mapToInt(Card::getPoint)
                .sum();
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public String getName() {
        return name;
    }

    public void setStatus(Status status) {
        this.status = status;
    };

    public Status getStatus() {
        return status;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public abstract List<Card> getInitialCards();
}
