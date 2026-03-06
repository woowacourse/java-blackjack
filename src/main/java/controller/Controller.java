package controller;

import domain.Dealer;
import domain.GameTable;
import domain.Hand;
import domain.Participant;
import domain.Player;
import dto.GameStatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import strategy.RandomStrategy;
import view.InputView;
import view.OutputView;

public class Controller {

    private GameTable gameTable;

    public void run() {
        setupPhase();
        gamePhase();
    }

    private void setupPhase() {
        gameTable = new GameTable();
        dealerSetup();
        playerSetup();
        displayInitCard();
    }

    private void displayInitCard() {
        List<GameStatus> gameStatuses = gameTable.gameStatus();
        OutputView.initCardStatus(gameStatuses);
    }

    private void dealerSetup() {
        Hand hand = new Hand(new RandomStrategy(), new ArrayList<>());
        Participant dealer = new Dealer("딜러", hand);
        dealer.draw();
        dealer.draw();
        gameTable.addParticipant(dealer);
    }

    private void playerSetup() {
        String playerNames = InputView.readPlayers();
        List<String> names = Arrays.stream(playerNames.split(",")).toList();
        OutputView.divideCards(names);

        for (String name : names) {
            Hand hand = new Hand(new RandomStrategy(), new ArrayList<>());
            Participant player = new Player(name, hand);
            player.draw();
            player.draw();
            gameTable.addParticipant(new Player(name, hand));
        }
    }

    private void gamePhase() {

    }
}
