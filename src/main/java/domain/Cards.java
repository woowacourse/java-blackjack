package domain;

import static domain.Gamer.GAMER_BUST_THRESHOLD;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Cards {

    protected final List<Card> cards;

    protected Cards(List<Card> cards) {
        validateDuplicate(cards);
        this.cards = cards;
    }

    public void validateDuplicate(List<Card> cards) {
        if (cards.size() != new HashSet<>(cards).size()) {
            throw new IllegalArgumentException("카드에 중복이 있습니다.");
        }
    }

    public int calculateScore() {
        boolean containAce = cards.stream()
                .anyMatch(Card::isAce);

        int lowAceTotal = cards.stream()
                .mapToInt(card -> card.getCardRank().getValue())
                .sum();
        return calculateAceValue(containAce, lowAceTotal);
    }

    public boolean hasCommonCard(Cards ohterCards) {
        return cards.stream()
                .anyMatch(ohterCards.cards::contains);
    }

    public void addCards(Cards otherCards) {
        validateBust(otherCards);
        cards.addAll(otherCards.cards);
    }

    public void validateBust(Cards cards) {
        if (isBust(cards)) {
            throw new IllegalArgumentException("버스트입니다.");
        }
    }

    public boolean isBust(Cards cards) {
        return cards.calculateScore() > GAMER_BUST_THRESHOLD;
    }

    private int calculateAceValue(boolean containAce, int lowAceTotal) {
        if (!containAce || lowAceTotal > GAMER_BUST_THRESHOLD) {
            return lowAceTotal;
        }

        int highAceTotal = lowAceTotal - CardRank.ACE.getValue() + CardRank.SPECIAL_ACE.getValue();

        if (highAceTotal > GAMER_BUST_THRESHOLD) {
            return lowAceTotal;
        }
        return highAceTotal;
    }

    public Card getFirst() {
        validateEmpty();
        return cards.getFirst();
    }

    protected void validateEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 없습니다.");
        }
    }

    public boolean isInitialHands() {
        return cards.size() == 2;
    }

    public boolean isBlackJack() {
        return isInitialHands() && calculateScore() == GAMER_BUST_THRESHOLD;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
