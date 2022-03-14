package domain.participant;

import domain.HitThreshold;
import domain.card.Card;
import domain.card.CardDeck;
import domain.card.Cards;

import java.util.List;
import java.util.Map;

public abstract class Participant {
    protected final Cards cards;
    protected final String name;

    protected Participant(final HitThreshold canAddCardThreshold, final String name) {
        this.cards = new Cards(canAddCardThreshold);
        this.name = name;
    }

    public boolean drawCard(boolean... response) {
        return cards.add(CardDeck.draw(), response);
    }

    protected List<Card> getCards() {
        return cards.getCards();
    }

    public Map<String, List<Card>> getNameWithCards() {
        return Map.of(name, getCards());
    }

    public Map<String, Integer> getTotalScoreWithName() {
        return Map.of(name, cards.calculateSum());
    }
}
