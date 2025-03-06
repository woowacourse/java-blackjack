package domain;

import java.util.Comparator;

public class Dealer {

    public static final int DEALER_HIT_THRESHOLD = 16;

    private final Cards cards;

    public Dealer(Cards cards) {
        validateInitialCardsSize(cards);
        this.cards = cards;
    }

    public Card openOneCard() {
        return cards.getCards().stream()
                .min(Comparator.comparingInt(card -> card.getNumber().getValue()))
                .orElse(cards.getCards().getFirst());
    }

    public boolean isSumUnderSixteen() {
        return sumCardNumbers() <= DEALER_HIT_THRESHOLD;
    }

    public boolean addOneCard(Card card) {
        return cards.addOneCard(card);
    }

    public int sumCardNumbers() {
        return cards.sumCardNumbers();
    }

    private void validateInitialCardsSize(Cards cards) {
        if (cards.getSize() != 2) {
            throw new IllegalArgumentException("[ERROR] 초기 카드는 두 장을 받아야 합니다.");
        }
    }

}
