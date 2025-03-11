package blackjack.domain.gambler;

import blackjack.domain.WinningDiscriminator;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hands {
    private final List<Card> cards;
    private static final int MIN_ACE_VALUE = 1;

    public Hands() {
        this.cards = new ArrayList<>();
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int sum = calculateSum();
        long aceCount = calculateAceCount();
        return adjustSumByAce(sum, (int) aceCount);
    }

    private int calculateSum() {
        return cards.stream()
                .mapToInt(Card::getTypeValue)
                .sum();
    }

    private long calculateAceCount() {
        return cards.stream()
                .filter(Card::isAce)
                .count();
    }

    protected int adjustSumByAce(int sum, int aceCount) {
        if (sum <= WinningDiscriminator.BLACK_JACK) {
            return sum;
        }
        while (aceCount > 0 && sum > WinningDiscriminator.BLACK_JACK) {
            sum -= (CardType.ACE.getValue() - MIN_ACE_VALUE);
            aceCount--;
        }
        return sum;
    }

    public boolean isScoreBelow(final int criteria) {
        return calculateScore() <= criteria;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
