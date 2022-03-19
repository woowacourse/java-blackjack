package blackjack.domain.card;

import blackjack.domain.card.pattern.Denomination;
import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int BLACK_JACK_POINT = 21;
    private static final int BLACK_JACK_CARDS_SIZE = 2;

    private final List<Card> cards;

    public Cards() {
        cards = new ArrayList<>();
    }

    public void save(final Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public int calculateTotalPoint() {
        int point = sumDenominationPoint();
        return calculateAcePoint(point);
    }

    private int calculateAcePoint(int point) {
        for (Card card : cards) {
            point = calculateAceAsSmallerStandard(point, card);
        }
        return point;
    }

    private int sumDenominationPoint() {
        return cards.stream()
            .mapToInt(Card::denominationPoint)
            .sum();
    }

    private int calculateAceAsSmallerStandard(int point, final Card card) {
        if (card.isAce() && point > BLACK_JACK_POINT) {
            point -= Denomination.ACE_INTERVAL;
        }
        return point;
    }

    public boolean isBlackJack() {
        return cards.size() == BLACK_JACK_CARDS_SIZE && calculateTotalPoint() == BLACK_JACK_POINT;
    }

    public boolean isBurst() {
        return cards.size() > BLACK_JACK_POINT;
    }
}
