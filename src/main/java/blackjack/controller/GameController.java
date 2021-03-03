package blackjack.controller;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Round;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.RoundInitializeStatusDto;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GameController {
    private final InputView inputView = new InputView(new Scanner(System.in));

    public void start() {
        Round round = initializeGame();
//        OutputView.showInitialStatus(round.initializeStatus());
        OutputView.showInitialStatus(new RoundInitializeStatusDto(round.getDealer(), round.getPlayers()));
    }

    private Round initializeGame() {
        List<String> playerNames = inputView.getPlayerNames();
        Dealer dealer = new Dealer();
        List<Player> players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        Round round = new Round(Card.getShuffledCards(), dealer, players);
        round.initialize();
        return round;
    }
}
