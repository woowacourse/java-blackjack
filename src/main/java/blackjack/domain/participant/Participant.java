package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

import java.util.List;

public abstract class Participant {
    public static final int BLACKJACK = 21;
    public static final int ACE_DIFFERENCE = 10;
    private final String name;
    private final Hand hand;

    public Participant(String name, List<Card> cards) {
        this.name = name;
        this.hand = new Hand(cards);
    }

    public String getName() {
        return name;
    }

    public abstract boolean isAvailableToTake();

    public List<Card> getUnmodifiableCards() {
        return hand.getUnmodifiableList();
    }

    public void takeCard(Card card) {
        hand.add(card);
    }

    public int sumCards() {
        List<Card> cardValues = hand.getUnmodifiableList();
        return cardValues.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public int sumCardsForResult() {
        List<Card> cardValues = hand.getUnmodifiableList();
        int aceCount = (int) cardValues.stream()
                .filter(Card::isAce)
                .count();
        int sum = cardValues.stream()
                .mapToInt(Card::getScore)
                .sum() + aceCount * ACE_DIFFERENCE;
        while (sum > BLACKJACK && aceCount > 0) {
            sum -= ACE_DIFFERENCE;
            aceCount--;
        }
        return sum;
    }

    public boolean isBlackjack() {
        return sumCardsForResult() == BLACKJACK && hand.size() == 2;
    }

    public boolean isBust() {
        return sumCards() > BLACKJACK;
    }

    public boolean isHit() {
        return !isBlackjack() && !isBust();
    }
}
