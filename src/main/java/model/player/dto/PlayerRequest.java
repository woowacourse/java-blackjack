package model.player.dto;

import model.cards.Cards;
import model.name.Name;

public class PlayerRequest {

    private final String name;
    private final Cards cards;

    private PlayerRequest(final String name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public static PlayerRequest from(final String name) {
        return new PlayerRequest(name, Cards.createPlayerCards());
    }

    public Name getName() {
        return Name.from(name);
    }

    public Cards getCards() {
        return cards;
    }
}
