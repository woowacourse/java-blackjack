package controller;

import domain.Cards;
import domain.Dealer;
import domain.Player;
import domain.PlayerName;
import domain.Players;

import java.util.ArrayList;
import java.util.List;

import static view.InputView.printErrorMessage;
import static view.InputView.readPlayerNames;

public class EntityCreator {
    private final Players players;
    private final Dealer dealer;

    public EntityCreator() {
        this.players = getValidPlayerNames();
        this.dealer = new Dealer(new Cards(new ArrayList<>()));
    }

    private Players getValidPlayerNames() {
        try {
            List<String> playerNames = readPlayerNames();
            List<Player> player = getPlayerList(playerNames);
            return new Players(player);
        } catch (RuntimeException exception) {
            printErrorMessage(exception);
            return getValidPlayerNames();
        }
    }

    private List<Player> getPlayerList(List<String> list) {
        List<Player> player = new ArrayList<>();
        for (String playerName : list) {
            player.add(new Player(new PlayerName(playerName), new Cards(new ArrayList<>())));
        }
        return player;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
