package controller;

import common.GamerDto;
import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Player;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {
    public void play() {
        Deck deck = Deck.create();
        List<String> playerNames = InputView.inputPlayerNames();
        List<Player> players = playerNames.stream()
                .map(name -> new Player(deck.getInitCards(), name))
                .collect(Collectors.toList());
        Dealer dealer = new Dealer(deck.getInitCards());

        OutputView.printInitGamersState(GamerDto.of(dealer), players.stream().map(GamerDto::of).collect(Collectors.toList()));
    }
}
