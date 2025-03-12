package blackjack.user;

import blackjack.card.Card;
import blackjack.card.CardDeck;
import blackjack.card.Denomination;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private static final int BLACKJACK_VALUE = 21;
    private static final int BLACKJACK_CARDS_SIZE = 2;

    protected final List<Card> cards;

    public Participant() {
        this.cards = new ArrayList<>();
    }

    public void addCards(final CardDeck cardDeck, final int count) {
        if (isImpossibleToAdd()) {
            throw new IllegalArgumentException("더 이상 카드를 추가할 수 없습니다.");
        }

        for (int i = 0; i < count; i++) {
            Card card = cardDeck.pickRandomCard();
            cards.add(card);
        }
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

    public List<Card> openCards() {
        return Collections.unmodifiableList(cards);
    }

    public abstract List<Card> openInitialCards();

    public abstract boolean isPossibleToAdd();

    public boolean isImpossibleToAdd() {
        return !isPossibleToAdd();
    }

    private boolean hasACE() {
        return cards.stream()
            .map(Card::denomination)
            .anyMatch(denomination -> denomination == Denomination.ACE);
    }
}
