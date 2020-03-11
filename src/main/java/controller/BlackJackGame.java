package controller;

import domain.card.Deck;
import domain.gamer.Player;
import domain.view.InputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {
    void play() {
        Deck deck = Deck.create();
        List<String> playerNames = InputView.inputPlayerNames();
        List<Player> players = playerNames.stream()
                .map(name -> new Player(deck.getInitCards(), name))
                .collect(Collectors.toList());
    }
}
