package domain.participant;

import domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private final Name name;
    private final List<Card> cards;

    public Participant(final Name name, final List<Card> cards) {
        this.name = name;
        this.cards = new ArrayList<>(cards);
    }

    abstract boolean isHittable();

    public void receiveCard(Card card) {
        this.cards.add(card);
    }

    public Score calculateScore() {
        boolean hasAce = hasAce();
        Score defaultScore = new Score(calculateDefaultScore());
        boolean canAddBonusScore = defaultScore.canAddBonusScore();

        if (hasAce && canAddBonusScore) {
            defaultScore = defaultScore.addBonusScore();
        }
        return defaultScore;
    }

    private int calculateDefaultScore() {
        return cards.stream()
                .mapToInt(Card::getDefaultScore)
                .sum();
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public boolean isBust() {
        Score score = new Score(calculateDefaultScore());
        return score.isBust();
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int getScore() {
        return calculateScore().getValue();
    }

}
