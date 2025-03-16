package model.participant;

import java.util.List;
import java.util.Map;
import model.card.Deck;

public final class Players {
    private final List<Player> players;

    public Players(Map<Name, Bet> registerInput, Deck deck) {
        this.players = registerInput.entrySet().stream()
                .map(registerEntry -> new Player(registerEntry.getKey(), registerEntry.getValue(), deck))
                .toList();
    }
}
