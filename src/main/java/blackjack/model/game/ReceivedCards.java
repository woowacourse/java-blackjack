package blackjack.model.game;

import blackjack.model.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class ReceivedCards {

    public static final int BLACKJACK_POINT = 21;
    public static final int ACE_INCREASABLE_POINT = 10;
    public static final int BLACKJACK_CARD_COUNT = 2;
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
                .filter(Card::isAceCard)
                .count());
    }

    private int adjustForAces(final int basePoint, final int aceCount) {
        return IntStream.range(0, aceCount)
                .reduce(basePoint,
                        (currentPoint, count) -> plusTenPoint(currentPoint)
                );
    }

    private int plusTenPoint(final int currentPoint) {
        if (isBust(currentPoint + ACE_INCREASABLE_POINT)) {
            return currentPoint;
        }
        return currentPoint + ACE_INCREASABLE_POINT;
    }

    public boolean isBust() {
        return isBust(calculateTotalPoint());
    }

    private boolean isBust(final int point) {
        return point > BLACKJACK_POINT;
    }

    public boolean isBlackJack() {
        return calculateTotalPoint() == BLACKJACK_POINT && cards.size() == BLACKJACK_CARD_COUNT;
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
    }
}
