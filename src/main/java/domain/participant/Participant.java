package domain.participant;

import domain.GameResult;
import domain.HitThreshold;
import domain.card.Card;
import domain.card.Cards;

import java.util.List;
import java.util.Map;

public abstract class Participant {
    protected final Cards cards;
    private final String name;

    protected Participant(final HitThreshold canAddCardThreshold, final String name) {
        this.cards = new Cards(canAddCardThreshold);
        this.name = name;
    }

    public boolean drawCard(final Card card) {
        return cards.add(card);
    }

    public boolean canDrawCard() {
        return cards.canAddCard();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public Map<String, List<Card>> getNameWithCards() {
        return Map.of(name, getCards());
    }

    public String getName() {
        return name;
    }

    public int getTotalScore() {
        return cards.calculateSum();
    }

    public abstract GameResult getGameResult(final Participant other);
}
