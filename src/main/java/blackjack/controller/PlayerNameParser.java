package blackjack.controller;

import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerNameParser {

    public static List<Player> parseNames(String playerNamesInput) {
        List<String> playerNames = List.of(playerNamesInput.split(","));

        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Gambler(playerName));
        }
        return players;
    }
}
