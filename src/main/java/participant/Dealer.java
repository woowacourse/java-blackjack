package participant;

import card.Card;
import card.Cards;
import score.Score;

import java.util.List;

public class Dealer {

    private static final int HIT_CONDITION = 17;

    private final Cards cards;

    public Dealer() {
        this.cards = new Cards();
    }

    public void addCard(final Card findCard) {
        cards.add(findCard);
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public void applyAceRule() {
        cards.applyAceRule();
    }

    public Score getScore() {
        return cards.calculateScore();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public boolean canHit() {
        return cards.isHit(HIT_CONDITION);
    }
}
