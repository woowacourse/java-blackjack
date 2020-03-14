package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int BLACKJACK_SIZE = 2;
    private static final int BLACKJACK_SCORE = 21;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public int getSize() {
        return cards.size();
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public int getScore() {
        Score score = Score.ZERO;

        for (Card card : cards) {
            score.add(card.getPoint());
        }
        if (hasAce()) {
            score.addAceBonusIfNotBust();
        }
        return score.getValue();
    }

    public List<Card> getValue() {
        return Collections.unmodifiableList(cards);
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return getScore() > BLACKJACK_SCORE;
    }
}