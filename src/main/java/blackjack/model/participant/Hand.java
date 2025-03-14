package blackjack.model.participant;

import static blackjack.model.constants.RuleConstants.BLACKJACK_HAND_COUNT;
import static blackjack.model.constants.RuleConstants.BLACKJACK_VALUE_TOTAL;
import static blackjack.model.constants.RuleConstants.SOFT_ACE_VALUE;
import static blackjack.model.constants.RuleConstants.SOFT_HAND_AVAILABLE_THRESHOLD;

import blackjack.model.card.Card;
import blackjack.model.card.CardValue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private final List<Card> hand;

    public Hand(List<Card> hand) {
        this.hand = new ArrayList<>(hand);
    }

    public void receiveHand(Card card) {
        hand.add(card);
    }

    public int calculateHandTotal() {
        int total = sumHandWithoutAce();
        if (hasAce()) {
            return total + calculateAceValue(total);
        }
        return total;
    }

    private int sumHandWithoutAce() {
        List<Card> handWithoutAce = getHandWithoutAce();
        int total = 0;
        for (Card card : handWithoutAce) {
            total += card.getCardValue().getDefaultValue();
        }
        return total;
    }

    private List<Card> getHandWithoutAce() {
        return hand.stream()
                .filter(card -> card.getCardValue() != CardValue.ACE)
                .toList();
    }

    private boolean hasAce() {
        return hand.stream()
                .anyMatch(card -> card.getCardValue() == CardValue.ACE);
    }

    private int calculateAceValue(int total) {
        int aceCount = getAceCountInHand();
        int aceValue = 0;
        if (total <= SOFT_HAND_AVAILABLE_THRESHOLD) {
            aceValue += SOFT_ACE_VALUE;
            aceCount--;
        }
        return aceValue + (aceCount * CardValue.ACE.getDefaultValue());
    }

    private int getAceCountInHand() {
        return (int) hand.stream()
                .filter(card -> card.getCardValue() == CardValue.ACE)
                .count();
    }

    public boolean isBlackjack() {
        return hand.size() == BLACKJACK_HAND_COUNT && calculateHandTotal() == BLACKJACK_VALUE_TOTAL;
    }

    public boolean isBust() {
        return BLACKJACK_VALUE_TOTAL < calculateHandTotal();
    }

    public Card getFirstHand() {
        return hand.getFirst();
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }
}
