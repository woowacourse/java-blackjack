package blackjack.domain.card;

import blackjack.domain.card.pattern.Denomination;
import java.util.ArrayList;
import java.util.List;

public class PlayerCards {

    private static final int BLACK_JACK_POINT = 21;
    private static final int BLACK_JACK_CARDS_SIZE = 2;

    private final List<Card> playerCards;

    public PlayerCards() {
        playerCards = new ArrayList<>();
    }

    public void save(final Card card) {
        playerCards.add(card);
    }

    public int calculateTotalPoint() {
        int point = sumDenominationPoint();
        return calculateAcePoint(point);
    }

    private int sumDenominationPoint() {
        return playerCards.stream()
            .mapToInt(Card::denominationPoint)
            .sum();
    }

    private int calculateAcePoint(int point) {
        for (Card card : playerCards) {
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
        return playerCards.size() == BLACK_JACK_CARDS_SIZE && calculateTotalPoint() == BLACK_JACK_POINT;
    }

    public boolean isBust() {
        return calculateTotalPoint() > BLACK_JACK_POINT;
    }

    public List<Card> getCards() {
        return List.copyOf(playerCards);
    }
}
