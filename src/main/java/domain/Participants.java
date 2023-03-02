package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Participants {
    private Dealer dealer;
    private Players players;

    public Participants(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Map<String, List<String>> getInitial() {
        Map<String, List<String>> initialCards = new HashMap<>();
        initialCards.put(dealer.getName().getName(), dealer.getCards());
        for (Player player : players.getPlayers()) {
            initialCards.put(player.getName().getName(),player.getCards());
        }
        return initialCards;
    }
}
