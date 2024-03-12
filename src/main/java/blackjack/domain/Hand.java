package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hand {
    private static final Score ACE_ADDITIONAL_SCORE = Score.from(10);
    private static final int INITIAL_COUNT = 2;

    private final List<Card> cards;

    private Hand(List<Card> cards) {
        this.cards = cards;
    }

    public static Hand of(Card... cards) {
        return new Hand(new ArrayList<>(Arrays.asList(cards)));
    }

    public Score calculate() {
        Score defaultScore = calculateDefaultScore();
        Score addedAceScore = defaultScore.add(ACE_ADDITIONAL_SCORE);
        if (hasAce() && addedAceScore.isNotBurst()) {
            return addedAceScore;
        }
        return defaultScore;
    }

    private Score calculateDefaultScore() {
        return cards.stream()
                .map(Card::getScore)
                .reduce(Score.from(0), Score::add);
    }

    public Hand add(Card card) {
        ArrayList<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);
        return new Hand(newCards);
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public boolean isBlackJack() {
        Score handScore = calculate();
        return isInitialHand() && handScore.isBlackJackScore();
    }

    private boolean isInitialHand() {
        return cards.size() == INITIAL_COUNT;
    }

    public boolean isNotBust() {
        Score handScore = calculate();
        return handScore.isNotBurst();
    }

    public boolean isBust() {
        return !isNotBust();
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
