package blackjack.controller;

import blackjack.domain.Game;
import blackjack.domain.dto.Status;
import blackjack.domain.player.Command;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class GameController {

    public void run() {
        Game game = generateGame();
        printInitialStatus(game);

        executePlayerTurn(game);
        executeDealerTurn(game);

        printTotalScore(game);
        printResult(game);
    }

    private Game generateGame() {
        try {
            return new Game(inputPlayerNames());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return generateGame();
        }
    }

    private List<String> inputPlayerNames() {
        return Arrays.stream(InputView.inputName())
                .map(String::trim)
                .collect(toList());
    }

    private void printInitialStatus(Game game) {
        Dealer dealer = game.getDealer();
        List<Player> players = game.getPlayersToList();

        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);

        List<Status> statuses = participants.stream()
                .map(Status::initialOf)
                .collect(toList());
        OutputView.printInitialStatus(statuses);
    }

    private void executePlayerTurn(Game game) {
        while (game.isPossibleToPlay()) {
            String name = game.getCurrentHitablePlayerName();
            Command command = inputCommand(name);
            Status status = game.playTurn(command);
            OutputView.printStatus(status);
        }
    }

    private Command inputCommand(String name) {
        try {
            return InputView.requestHitOrStay(name);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputCommand(name);
        }
    }

    private void executeDealerTurn(Game game) {
        while (game.doesDealerNeedToDraw()) {
            game.doDealerDraw();
            OutputView.printDealerHitMessage();
        }
    }

    private void printTotalScore(Game game) {
        Dealer dealer = game.getDealer();
        List<Player> players = game.getPlayersToList();

        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);

        List<Status> statuses = participants.stream()
                .map(Status::of)
                .collect(toList());
        OutputView.printTotalScore(statuses);
    }

    private void printResult(Game game) {
        OutputView.printResult(game.getPlayerResults(), game.getDealerResult());
    }
}
