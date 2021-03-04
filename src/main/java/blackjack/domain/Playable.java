package blackjack.domain;

import java.util.List;

public abstract class Playable {
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
            return 1;
        }

        if (lose(counterpart, playerSum)) {
            return -1;
        }

        return 0;
    }

    private boolean lose(int counterpart, int playerSum) {
        if (counterpart <= 21 && playerSum > 21) {
            return true;
        }
        if (counterpart <= 21 && counterpart > playerSum) {
            return true;
        }
        return false;
    }

    private boolean win(int counterpart, int playerSum) {
        if (counterpart > 21 && playerSum <= 21) {
            return true;
        }
        if (playerSum <= 21 && counterpart < playerSum) {
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
        int sum = cardValues.stream().mapToInt(Card::getScore).sum() + aceCount * 10;
        while (sum > 21 && aceCount > 0) {
            sum -= 10;
            aceCount--;
        }
        return sum;
    }
}
