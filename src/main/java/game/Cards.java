package game;

import constant.TrumpNumber;
import java.util.List;

public class Cards {

    public static final int BUST_STANDARD = 21;
    public static final int SOFT_ACE_DIFFERENCE = 10;
    public static final int HIGHEST_SCORE = 21;

    private final List<Card> cards;
    private final boolean isBlackJack;

    public Cards(List<Card> cards) {
        this.cards = cards;
        isBlackJack = checkBlackJack();
    }

    public boolean addOneCard(Card card) {
        cards.add(card);
        return sumCardNumbers() <= BUST_STANDARD;
    }

    public int sumCardNumbers() {
        int sum = calculateSum();
        for (int i = 0; i < countAces(); i++) {
            sum = processAce(sum);
        }
        return sum;
    }

    public Card drawCard() {
        return cards.removeLast();
    }

    public boolean checkBlackJack() {
        return sumCardNumbers() == HIGHEST_SCORE;
    }

    private int calculateSum() {
        return cards.stream()
                .mapToInt(card -> card.getNumber().getValue())
                .sum();
    }

    private int countAces() {
        return (int) cards.stream()
                .filter(card -> card.getNumber() == TrumpNumber.ACE)
                .count();
    }

    private int processAce(int sum) {
        if (sum > BUST_STANDARD) {
            sum -= SOFT_ACE_DIFFERENCE;
        }
        return sum;
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean isBlackJack() {
        return isBlackJack;
    }
}
