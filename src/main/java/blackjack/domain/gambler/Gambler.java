package blackjack.domain.gambler;

import blackjack.domain.WinningDiscriminator;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Gambler {
    private static final int MIN_ACE_VALUE = 1;
    private final List<Card> cards;
    private final Name name;

    public Gambler(final Name name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public boolean isNameEquals(final Name name) {
        return Objects.equals(name, this.name);
    }

    public boolean isScoreBelow(final int criteria) {
        return calculateScore() <= criteria;
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

    public Name getName() {
        return name;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public abstract List<Card> getInitialCards();
}
