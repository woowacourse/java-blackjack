package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int INITIAL_CARD_COUNT = 2;
    private static final int BLACKJACK_SCORE = 21;
    private static final int ACE_ADDITIONAL_SCORE = 10;

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int results = 0;
        for (Card holdCard : cards) {
            results += holdCard.getCardScore();
        }

        return applyAce(results);
    }

    public boolean isBlackjack() {
        return cards.size() == INITIAL_CARD_COUNT && calculateScore() == BLACKJACK_SCORE;
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK_SCORE;
    }

    private int applyAce(int results) {

        if (isSoftHand(results)) {
            return results + ACE_ADDITIONAL_SCORE;
        }
        return results;
    }

    public boolean isSoftHand(int results) {
        boolean isAceExist = cards.stream().anyMatch(Card::isAce);
        return isAceExist && (results + ACE_ADDITIONAL_SCORE) <= BLACKJACK_SCORE;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
