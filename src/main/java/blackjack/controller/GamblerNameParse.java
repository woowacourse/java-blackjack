package blackjack.controller;

import blackjack.domain.player.Gambler;

import java.util.ArrayList;
import java.util.List;

public class GamblerNameParse {

    public static List<Gambler> parseNames(String playerNamesInput) {
        List<String> playerNames = List.of(playerNamesInput.split(","));

        List<Gambler> gamblers = new ArrayList<>();
        for (String playerName : playerNames) {
            gamblers.add(new Gambler(playerName));
        }
        return gamblers;
    }
}
