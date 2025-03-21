package domain.participant;

import domain.BlackJackWinningStatus;
import domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public abstract class Participant {
    public static final int BURST_UPPER_BOUND = 21;
    public static final int BLACK_JACK_SIZE = 2;
    public static final int BLACK_JACK_VALUE = 21;

    private final String name;
    private final List<Card> cards;

    public Participant(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public abstract List<Card> getFirstShownCard();

    public abstract boolean canPick();

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBlackJack() {
        return (cards.size() == BLACK_JACK_SIZE) && (getTotalValue() == BLACK_JACK_VALUE);
    }

    public int getTotalValue() {
        final int constTotalValue = calculateConstValue(cards);
        final int aceCount = getAceCount();
        return calculateTotalValue(constTotalValue, aceCount);
    }

    private int calculateConstValue(List<Card> cards) {
        return cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(Card::getValue)
                .sum();
    }

    private int getAceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private int calculateTotalValue(int baseValue, int aceCount) {
        return IntStream.rangeClosed(0, aceCount)
                .map(aceAsOneCount -> {
                    final int aceAsElevenCount = aceCount - aceAsOneCount;
                    return calculateAceValue(baseValue, aceAsOneCount, aceAsElevenCount);
                })
                .filter(candidateResult -> !BlackJackWinningStatus.isBurstBy(candidateResult))
                .max()
                .orElse(baseValue + aceCount);
    }

    private int calculateAceValue(int baseValue, int aceAsOneCount, int aceAsElevenCount) {
        final int totalAceAsElevenValue = aceAsElevenCount * 11;
        final int totalAceAsOneValue = aceAsOneCount * 1;
        return baseValue + totalAceAsElevenValue + totalAceAsOneValue;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

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
