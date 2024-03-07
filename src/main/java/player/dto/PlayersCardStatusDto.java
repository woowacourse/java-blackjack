package player.dto;

import card.Cards;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import player.Name;
import player.Player;
import player.Players;

public record PlayersCardStatusDto(Map<Name, Cards> cards) {

    public static PlayersCardStatusDto of(Players players) {
        Map<Name, Cards> cards = new HashMap<>();

        for (Player player : players.getPlayers()) {
            cards.put(player.getName(), player.getCards());
        }
        return new PlayersCardStatusDto(cards);
    }

    public List<Name> getNames() {
        return cards.keySet()
                .stream().toList();
    }

    public int size() {
        return cards.size();
    }
}
