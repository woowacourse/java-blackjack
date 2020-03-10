package domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
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

    public boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public boolean isBlackJack() {
        return (cards.size() == 2) && (getScore() == 21);
    }

    public boolean isLessThan(int criteria){
        return getScore() <= criteria;
    }
}