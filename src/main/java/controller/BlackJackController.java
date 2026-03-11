package controller;

import domain.BlackJackGame;
import domain.CardCreationStrategy;
import domain.Player;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import view.InputView;
import view.OutputView;

// 게임의 총 흐름을 담당.
public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;
    private final CardCreationStrategy strategy;

    public BlackJackController(InputView inputView, OutputView outputView, CardCreationStrategy strategy) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.strategy = strategy;
    }

    public void doGameProcess() {
        BlackJackGame game = retry(this::readyGame);
        outputView.printInitialStates(
                game.getDealerGameSettingResult(),
                game.getPlayersGameSettingResults()
        );

        //2. 게임을 진행하라
        while (true) {
            Optional<Player> currentPlayer = game.whoseTurn();
            if (currentPlayer.isEmpty()) {
                break;
            }

            String currentUserName = currentPlayer.get().getName();
            outputView.printHitOrStandPrompt(currentUserName);
            String hitOrStand = inputView.readHitOrStand();
            if (hitOrStand.equals("y")) {
                game.doHitProcess(); //TODO: 출력이 야기되어야 합니다.
            } else {
                game.doStandProcess();
            }
        }
//
//        //3. 게임 결과를 구하라
//        blackJackGame.end(this);
    }

    private BlackJackGame readyGame() {
        outputView.printNamePrompt();
        List<String> playerNames = inputView.readNames();
        return BlackJackGame.ready(playerNames, strategy);
    }

    private <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }
}

