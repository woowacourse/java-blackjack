package domain.participant;

import domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Participant {

    private static final int BURST_UPPER_BOUND = 21;

    private final String name;
    private final List<Card> cards;
    private Betting betting;

    protected Participant(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getBettingAmount() {
        return betting.amount();
    }

    public void startBetting(int bettingAmount) {
        this.betting = new Betting(bettingAmount);
    }

    public String getName() {
        return name;
    }

    public static boolean isBust(int value) {
        return value > BURST_UPPER_BOUND;
    }

    public boolean isBlackJack() {
        return getTotalValue() == BURST_UPPER_BOUND && cards.size() == 2;
    }

    public abstract List<Card> getShownCard();

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }

    public int getTotalValue() {
        final int constTotalValue = calculateConstValue(cards);
        final int aceCount = getAceCount();
        return calculateTotalValue(constTotalValue, aceCount);
    }

    private int calculateTotalValue(int baseValue, int aceCount) {
        int candidateResult = baseValue;
        for (int oneValueCount = 0; oneValueCount <= aceCount; ++oneValueCount) {
            candidateResult = baseValue + (oneValueCount * 1) + ((aceCount - oneValueCount) * 11);
            if (Participant.isBust(candidateResult)) {
                continue;
            }
            return candidateResult;
        }
        return candidateResult;
    }

    private int getAceCount() {
        return (int) cards.stream()
            .filter(Card::isAce)
            .count();
    }

    private int calculateConstValue(List<Card> cards) {
        return cards.stream()
            .filter(card -> !card.isAce())
            .mapToInt(Card::getValue)
            .sum();
    }

    public abstract boolean canPick();

    public abstract boolean isPlayer();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "Participant{" +
            "name='" + name + '\'' +
            ", cards=" + cards +
            '}';
    }
}
