package participant;

import card.Deck;
import java.util.List;
import java.util.Map;
import card.CardHand;

public final class Players {
    private final List<Player> players;

    public Players(Map<Name, Bet> registerInput, Deck deck) {
        this.players = registerInput.entrySet().stream()
                .map(registerEntry -> new Player(registerEntry.getKey(), registerEntry.getValue(),
                        CardHand.drawInitialHand(deck)))
                .toList();
    }

    public List<Player> getPlayers() {
        return players;
    }
}
