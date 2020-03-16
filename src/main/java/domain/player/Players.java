package domain.player;

import domain.card.Cards;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<User> users;

    public Players(Cards cards, List<String> playerNames) {
        this.users = playerNames.stream()
                .map(name -> new Player(name, cards.giveCard(), cards.giveCard()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(users.stream()
                .map(player -> (Player) player)
                .collect(Collectors.toList()));
    }

}
