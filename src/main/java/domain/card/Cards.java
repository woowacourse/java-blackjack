package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int BLACKJACK_SIZE = 2;
    private static final int BLACKJACK_SCORE = 21;
    private static final int INIT_CARD_INDEX = 0;

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
            score = score.add(card.getPoint());
        }
        if (hasAce()) {
            score = score.addAceBonusIfNotBust();
        }
        return score.getValue();
    }

    public boolean isBlackJack() {
        return (cards.size() == BLACKJACK_SIZE) && (getScore() == BLACKJACK_SCORE);
    }

    public boolean isLessThan(final int criteria) {
        return getScore() <= criteria;
    }

    public List<Card> getValue() {
        return Collections.unmodifiableList(cards);
    }

    public String getFirstCardInfo() {
        Card firstCard = cards.get(INIT_CARD_INDEX);

        return firstCard.getCardInfo();
    }

    public boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return getScore() > BLACKJACK_SCORE;
    }
}