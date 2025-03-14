package blackjack.model.game;

import blackjack.model.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ReceivedCards {
    public static final int BONUS_ACE_POINT = 10;
    public static final int BUST_POINT = 21;
    private final List<Card> cards = new ArrayList<>();

    public void receive(Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public int calculateTotalPoint() {
        int totalPoint = calculateMinimumPoint();
        if (hasAce() && !isBust(totalPoint + BONUS_ACE_POINT)) {
            return totalPoint + BONUS_ACE_POINT;
        }
        return totalPoint;
    }

    public boolean hasAce() {
        return cards.stream().anyMatch(Card::isAceCard);
    }

    public boolean isBust() {
        return isBust(calculateTotalPoint());
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }

    public Stream<Card> stream() {
        return cards.stream();
    }

    public long getSpecialCardCount () {
        return cards.stream().filter(Card::isSpecialCard).count();
    }

    public long getAceCardCount() {
        return cards.stream().filter(Card::isAceCard).count();
    }

    private int calculateMinimumPoint() {
        return cards.stream()
                .mapToInt(Card::getPoint)
                .sum();
    }

    private boolean isBust(int point) {
        return point > BUST_POINT;
    }
}
