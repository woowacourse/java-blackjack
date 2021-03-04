package blackjack.domain;

import java.util.List;

public class Dealer implements Playable {
    private final Cards cards;

    public Dealer(List<Card> cards) {
        this.cards = new Cards(cards);
    }

    @Override
    public String getName() {
        return "딜러";
    }

    @Override
    public List<Card> getUnmodifiableCards() {
        return cards.getUnmodifiableList();
    }

    public boolean isAvailableToTake() {
        return sumCards() <= 17;
    }

    @Override
    public int result(int playerSum) {
        int dealerSum = sumCardsForResult();

        if (playerSum > 21 && dealerSum <= 21) {
            return 1;
        }
        if (dealerSum <= 21 && playerSum < dealerSum) {
            return 1;
        }

        if (playerSum <= 21 && dealerSum > 21) {
            return -1;
        }
        if (playerSum <= 21 && playerSum > dealerSum) {
            return -1;
        }

        return 0;
    }

    @Override
    public void takeCard(Card card) {
        cards.add(card);
    }

    @Override
    public int sumCards() {
        List<Card> cardValues = cards.getUnmodifiableList();
        return cardValues.stream().mapToInt(Card::getScore).sum();
    }

    @Override
    public int sumCardsForResult() {
        List<Card> cardValues = cards.getUnmodifiableList();
        int sum = cardValues.stream().mapToInt(Card::getScore).sum();
        int aceCount = (int) cardValues.stream().filter(Card::isAce).count();
        sum += aceCount * 10;
        for (int i = 0; i < aceCount; i++) {
            if (sum > 21) {
                sum -= 10;
                continue;
            }
            break;
        }
        return sum;
    }
}
