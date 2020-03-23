package blackjack.domain.card;

import blackjack.domain.card.component.CardNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cards {
    private static int BLACK_JACK = 21;
    private static int BLACK_JACK_SIZE = 2;
    private List<Card> cards;

    public Cards() {
        cards = new ArrayList<>();
    }

    public Cards(List<Card> cards) {
        Objects.requireNonNull(cards);
        this.cards = cards;
    }

    public void add(Card card) {
        Objects.requireNonNull(card);
        cards.add(card);
    }

    public int computePoint() {
        int totalPoint = computeTotalPoint();
        totalPoint = handleAce(totalPoint);
        totalPoint = handleBust(totalPoint);
        return totalPoint;
    }

    private int computeTotalPoint(){
        return cards.stream()
                .mapToInt(x -> x.getCardPoint())
                .sum();
    }

    public boolean hasAce() {
        int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();
        return aceCount > 0;
    }

    private int handleAce(int totalPoint) {
        if (hasAce() && totalPoint < BLACK_JACK) {
            totalPoint += CardNumber.ACE_DIFF;
        }
        return totalPoint;
    }

    private int handleBust (int totalPoint) {
        if (totalPoint > BLACK_JACK) {
            return 0;
        }
        return totalPoint;
    }

    public int getDiffWithBlackJack() {
        return computePoint() - BLACK_JACK;
    }

    public boolean isBlackJack() {
        return cards.size() == BLACK_JACK_SIZE && computePoint() == BLACK_JACK;
    }

    public boolean isBust() {
        return computePoint() <= 0;
    }

    public boolean isNormal() {
        return !isBlackJack() && !isBust();
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
