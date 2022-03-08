package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards() {
        cards = new ArrayList<>();
    }

    public void save(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public int calculateTotalPoint() {
        int point = sumDenominationPoint();

        for (Card card : cards) {
            point = calculateAceAsSmallerStandard(point, card);
        }
        return point;
    }

    private int calculateAceAsSmallerStandard(int point, Card card) {
        if (card.isAce() && point > 21) {
            point -= 10;
        }
        return point;
    }

    private int sumDenominationPoint() {
        return cards.stream()
                .mapToInt(card -> card.getDenomination().getPoint())
                .sum();
    }
}
