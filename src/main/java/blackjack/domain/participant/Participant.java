package blackjack.domain.participant;

import blackjack.domain.HandGenerator;
import blackjack.domain.Name;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import java.util.List;

public abstract class Participant {
    private final Name name;
    private final Hand hand;

    public Participant(Name name, HandGenerator handGenerator) {
        this.name = name;
        this.hand = handGenerator.generate();
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public abstract List<Card> getInitialOpenedCards();

    protected List<Card> getCardsByCount(int count) {
        List<Card> cards = getCards();
        return cards.subList(0, count);
    }
}
