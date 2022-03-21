package blackjack.domain.card;

import blackjack.domain.card.pattern.Denomination;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int BLACK_JACK_POINT = 21;
    private static final int BLACK_JACK_CARDS_SIZE = 2;

    private final List<Card> hand;

    public Hand() {
        hand = new ArrayList<>();
    }

    public void save(final Card card) {
        hand.add(card);
    }

    public int calculateTotalPoint() {
        int point = sumDenominationPoint();
        return calculateAcePoint(point);
    }

    private int sumDenominationPoint() {
        return hand.stream()
            .mapToInt(Card::denominationPoint)
            .sum();
    }

    private int calculateAcePoint(int point) {
        for (Card card : hand) {
            point = calculateAceAsSmallerStandard(point, card);
        }
        return point;
    }

    private int calculateAceAsSmallerStandard(int point, final Card card) {
        if (card.isAce() && point > BLACK_JACK_POINT) {
            point -= Denomination.ACE_INTERVAL;
        }
        return point;
    }

    public boolean isBlackJack() {
        return hand.size() == BLACK_JACK_CARDS_SIZE && calculateTotalPoint() == BLACK_JACK_POINT;
    }

    public boolean isBust() {
        return calculateTotalPoint() > BLACK_JACK_POINT;
    }

    public List<Card> getHand() {
        return List.copyOf(hand);
    }
}
