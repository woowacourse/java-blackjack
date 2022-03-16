package blackjack;

import blackjack.domain.Game;
import blackjack.domain.player.Command;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class GameRunner {

    public void run() {
        Game game = generateGame();
        OutputView.printInitialStatus(game.getDealer(), game.getPlayers());

        executePlayerTurn(game);
        executeDealerTurn(game);

        OutputView.printTotalScore(game.getDealer(), game.getPlayers());
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

    private void executePlayerTurn(Game game) {
        while (game.isPossibleToPlay()) {
            String name = game.getCurrentHitablePlayerName();
            game.playTurn(inputCommand(name));
            OutputView.printCurrentCards(game.getCurrentPlayer());
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
        while (game.dealerCanDraw()) {
            game.doDealerDraw();
            OutputView.printDealerHitMessage();
        }
    }

    private void printResult(Game game) {
        OutputView.printResult(game.getPlayerResults(), game.getDealerResult());
    }
}
