package blackjack.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ReceivedCards {

    private final List<Card> cards = new ArrayList<>();

    public void receive(Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public int calculateTotalPoint() {
        int basePoint = calculateTotalDefaultPoint();
        int aceCount = countAces();
        return adjustForAces(basePoint, aceCount);
    }

    private int calculateTotalDefaultPoint() {
        return cards.stream()
                .mapToInt(Card::getPoint)
                .sum();
    }

    private int countAces() {
        return Math.toIntExact(cards.stream()
                .filter(card -> card.equalsCardType(CardType.ACE))
                .count());
    }

    private int adjustForAces(int basePoint, int aceCount) {
        return IntStream.range(0, aceCount).reduce(basePoint, (currentPoint, i) -> plusTenPoint(currentPoint));
    }

    private int plusTenPoint(int currentPoint) {
        if (!isBust(currentPoint + 10)) {
            currentPoint += 10;
        }
        return currentPoint;
    }

    public boolean isBust() {
        return isBust(calculateTotalPoint());
    }

    private boolean isBust(int point) {
        return point > 21;
    }
}
