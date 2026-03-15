package model.paticipant;

import java.util.List;
import model.card.Card;
import model.card.Hand;

public abstract class Participant {

    private static final int BUST_LIMIT = 21;
    private static final int ACE_BONUS_SCORE = 10;
    private static final int BLACKJACK_CARD_COUNT = 2;

    private final Name name;
    private final Hand hand;

    protected Participant(String name) {
        this.name = new Name(name);
        this.hand = new Hand();
    }

    public int calculateTotalScore() {
        int score = this.hand.calculateScore();
        int aceCount = this.hand.countAce();

        while (aceCount > 0 && score + ACE_BONUS_SCORE <= BUST_LIMIT) {
            score += ACE_BONUS_SCORE;
            aceCount--;
        }
        return score;
    }

    public boolean isBust() {
        return calculateTotalScore() > BUST_LIMIT;
    }

    public boolean isBlackjack() {
        return hand.size() == BLACKJACK_CARD_COUNT && calculateTotalScore() == BUST_LIMIT;
    }

    public void addCard(Card card) {
        this.hand.add(card);
    }

    public String getName() {
        return name.name();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public abstract boolean canHit();
}
