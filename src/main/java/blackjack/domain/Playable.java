package blackjack.domain;

import java.util.List;

public abstract class Playable {
    public static final int BLACK_JACK = 21;
    private static final int WIN = 1;
    private static final int DRAW = 0;
    private static final int LOSE = -1;
    private static final int ACE_EXTRA_VALUE = 10;
    private final String name;
    private final Cards cards;

    public Playable(String name, List<Card> cards) {
        this.name = name;
        this.cards = new Cards(cards);
    }

    public String getName() {
        return name;
    }

    public abstract boolean isAvailableToTake();

    public List<Card> getUnmodifiableCards() {
        return cards.getUnmodifiableList();
    }

    public int result(int counterpart) {
        int playerSum = sumCardsForResult();

        if (win(counterpart, playerSum)) {
            return WIN;
        }

        if (lose(counterpart, playerSum)) {
            return LOSE;
        }

        return DRAW;
    }

    private boolean lose(int counterpart, int playerSum) {
        if (counterpart <= BLACK_JACK && playerSum > BLACK_JACK) {
            return true;
        }
        if (counterpart <= BLACK_JACK && counterpart > playerSum) {
            return true;
        }
        return false;
    }

    private boolean win(int counterpart, int playerSum) {
        if (counterpart > BLACK_JACK && playerSum <= BLACK_JACK) {
            return true;
        }
        if (playerSum <= BLACK_JACK && counterpart < playerSum) {
            return true;
        }
        return false;
    }

    public void takeCard(Card card) {
        cards.add(card);
    }

    public int sumCards() {
        List<Card> cardValues = cards.getUnmodifiableList();
        return cardValues.stream().mapToInt(Card::getScore).sum();
    }

    public int sumCardsForResult() {
        List<Card> cardValues = cards.getUnmodifiableList();
        int aceCount = (int) cardValues.stream().filter(Card::isAce).count();
        int sum = cardValues.stream().mapToInt(Card::getScore).sum() + aceCount * ACE_EXTRA_VALUE;
        while (sum > BLACK_JACK && aceCount > 0) {
            sum -= ACE_EXTRA_VALUE;
            aceCount--;
        }
        return sum;
    }
}
