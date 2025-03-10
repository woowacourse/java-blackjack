package model;

import java.util.Map;
import java.util.Set;
import model.card.Card;
import model.cards.Cards;

public class Players {

    private final Map<String, Cards> players;

    public Players(final Map<String, Cards> players) {
        this.players = players;
    }

    public Cards findCardsByName(final String name) {
        if (!players.containsKey(name)) {
            throw new IllegalArgumentException();
        }
        return players.get(name);
    }

    public void addCardByName(final String name, final Card card) {
        findCardsByName(name).addCard(card);
    }

    public boolean checkIsBustByName(final String name) {
        return findCardsByName(name).isBust();
    }

    public int getResultByName(final String name) {
        return findCardsByName(name).calculateResult();
    }

    public Set<String> getNames() {
        return players.keySet();
    }
}
