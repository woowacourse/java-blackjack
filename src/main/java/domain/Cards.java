package domain;

import domain.constant.TrumpNumber;
import java.util.List;

public class Cards {

    public static final int BUST_STANDARD = 21;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public boolean addOneCard(Card card) {
        cards.add(card);
        return sumCardNumbers() <= BUST_STANDARD;
    }

    public int sumCardNumbers() {
        int aceCount = (int) cards.stream()
                .filter(card -> card.getNumber() == TrumpNumber.ACE)
                .count();

        int sum = cards.stream()
                .mapToInt(card -> card.getNumber().getValue())
                .sum();

        for (int i = 0; i < aceCount; i++) {
            sum = processAce(sum);
        }
        return sum;
    }

    private int processAce(int sum) {
        if (sum > BUST_STANDARD) {
            sum -= 10;
        }
        return sum;
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
