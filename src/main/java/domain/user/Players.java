package domain.user;

import domain.card.Deck;
import utils.StringUtils;
import view.InputView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players = new ArrayList<>();

    public Players(List<String> playerNames) {
        StringUtils.checkNameNullAndEmpty(playerNames);
        for (String playerName : playerNames) {
            players.add(new Player(playerName, InputView.inputBettingMoney(playerName)));
        }
    }

    public void receiveFirstCards(Deck deck) {
        for (Player player : players) {
            player.receiveFirstCards(deck);
        }
    }

    public List<String> getNames() {
        return players.stream().
                map(player -> player.getName().getName())
            .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }

}
