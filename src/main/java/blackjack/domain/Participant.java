package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    protected final List<Card> cards;

    public Participant() {
        this.cards = new ArrayList<>();
    }

    public void addCards(Card... cards) {
        this.cards.addAll(Arrays.asList(cards));
    }

    public int calculateDenominations() {
        int sum = cards.stream()
                .map(Card::denomination)
                .map(Denomination::getValues)
                .map(List::getFirst)
                .reduce(0, Integer::sum);
        if(hasACE()) {
            sum = Denomination.changeAceValue(sum);
        }

        return sum;
    }

    public boolean hasTwoCards() {
        return cards.size() == 2;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public abstract boolean isPossibleToAdd();

    private boolean hasACE() {
        return cards.stream()
                .map(Card::denomination)
                .anyMatch(denomination -> denomination == Denomination.ACE);
    }
}
