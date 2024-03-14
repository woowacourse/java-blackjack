package domain.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private static final int FIRST_CARD_INDEX = 0;

    private final List<Card> cards;
    private final Score score;

    public Hand() {
        this.cards = new ArrayList<>();
        this.score = new Score();
    }

    public void addCard(Card card) {
        cards.add(card);
        calculateScore();
    }

    public Score calculateScore() {
        score.sumAllCards(cards);
        if (hasAce()) {
            score.decideScore();
        }
        return score;
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public boolean cannotBust() {
        return score.cannotBust();
    }

    public boolean cannotDealerHit() {
        return score.cannotDealerHit();
    }

    public boolean hasScore(int comparedScore) {
        return score.hasScore(comparedScore);
    }

    public Card pickFirstCard() {
        return cards.get(FIRST_CARD_INDEX);
    }

    public boolean hasSize(int size) {
        return cards.size() == size;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
