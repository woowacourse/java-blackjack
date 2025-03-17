package blackjack.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardHand {

    private static final int BLACKJACK_VALUE = 21;
    private static final int BLACKJACK_CARDS_SIZE = 2;

    private final List<Card> cards;
    private final int additionThreshold;

    public CardHand(final int additionThreshold) {
        this.cards = new ArrayList<>();
        this.additionThreshold = additionThreshold;
    }

    public void addCards(final List<Card> cards) {
        if (isImpossibleToAdd()) {
            throw new IllegalArgumentException("더 이상 카드를 추가할 수 없습니다.");
        }
        this.cards.addAll(cards);
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARDS_SIZE
            && calculateDenominations() == BLACKJACK_VALUE;
    }

    public boolean isBust() {
        return calculateDenominations() > BLACKJACK_VALUE;
    }

    public int calculateDenominations() {
        int sum = cards.stream()
            .map(Card::getDenominationNumber)
            .map(List::getFirst)
            .reduce(0, Integer::sum);

        if (hasACE()) {
            return Denomination.convertAceValue(sum, BLACKJACK_VALUE);
        }
        return sum;
    }

    private boolean hasACE() {
        return cards.stream()
            .map(Card::denomination)
            .anyMatch(denomination -> denomination == Denomination.ACE);
    }

    public List<Card> openCards() {
        return Collections.unmodifiableList(cards);
    }

    public List<Card> openInitialCards(final int count) {
        return cards.subList(0, Math.min(count, cards.size()));
    }

    public boolean isPossibleToAdd() {
        return calculateDenominations() < additionThreshold;
    }

    private boolean isImpossibleToAdd() {
        return !isPossibleToAdd();
    }
}
