package domain.participant;

import domain.HitThreshold;
import domain.card.Card;
import domain.card.CardDeck;
import domain.card.Cards;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Participant {
    protected final Cards cards;
    protected final String name;

    public Participant(final HitThreshold canAddCardThreshold, final String name) {
        this.cards = new Cards(canAddCardThreshold);
        this.name = name;
    }

    public boolean drawCard(boolean... response) {
        return cards.add(CardDeck.draw(), response);
    }

    protected List<Card> getCards() {
        return cards.getCards();
    }

    public Map<String, List<Card>> getCardsWithName() {
        return new LinkedHashMap<>(Map.of(name, getCards()));
    }

    public Map<String, Integer> getTotalScoreWithName() {
        return new LinkedHashMap<>(Map.of(name, cards.calculateSum()));
    }
}
