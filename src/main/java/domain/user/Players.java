package domain.user;

import domain.Moneys;
import domain.Names;
import domain.card.CardDeck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(Names names, Moneys moneys) {
        players = new ArrayList<>();
        for (String name : names.get()) {
            Player player = new Player(name, moneys.getValue(name));
            players.add(player);
        }
    }

    public List<Player> get() {
        return Collections.unmodifiableList(players);
    }

    public void firstDraw(final CardDeck cardDeck) {
        for (Player player : players) {
            player.firstDraw(cardDeck);
        }
    }

    public List<String> getStatus() {
        return players.stream()
                .map(Player::getStatus)
                .collect(Collectors.toList());
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }
}
