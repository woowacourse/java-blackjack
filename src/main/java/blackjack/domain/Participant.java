package blackjack.domain;

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

    public Outcome result(int counterpart) {
        int playerSum = sumCardsForResult();

        if (win(counterpart, playerSum)) {
            return Outcome.WIN;
        }

        if (lose(counterpart, playerSum)) {
            return Outcome.LOSE;
        }

        return Outcome.DRAW;
    }

    private boolean lose(int counterpart, int playerSum) {
        if (counterpart <= BLACKJACK && playerSum > BLACKJACK) {
            return true;
        }
        return counterpart <= BLACKJACK && counterpart > playerSum;
    }

    private boolean win(int counterpart, int playerSum) {
        if (counterpart > BLACKJACK && playerSum <= BLACKJACK) {
            return true;
        }
        return playerSum <= BLACKJACK && counterpart < playerSum;
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
}
