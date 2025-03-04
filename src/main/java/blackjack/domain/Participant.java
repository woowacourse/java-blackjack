package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Participant {

    private final List<Card> cards;

    public Participant() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
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

    private boolean hasACE() {
        return cards.stream()
                .map(Card::denomination)
                .anyMatch(denomination -> denomination == Denomination.ACE);
    }
}
