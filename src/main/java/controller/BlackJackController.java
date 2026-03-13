package controller;

import domain.BlackJackGame;
import domain.CardCreationStrategy;
import domain.Player;
import dto.GameResultDto;
import dto.ParticipantDto;
import java.util.List;
import java.util.function.Supplier;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;
    private final CardCreationStrategy strategy;

    public BlackJackController(InputView inputView,
                               OutputView outputView,
                               CardCreationStrategy strategy) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.strategy = strategy;
    }

    public void doGameProcess() {
        BlackJackGame game = retry(this::readyGame);
        outputView.printInitialStates(
                game.getDealerGameSettingState(),
                game.getPlayersGameSettingStates()
        );

        playPlayersTurn(game);
        playDealerTurn(game);

        GameResultDto gameResults = game.getGameResults();
        outputView.printGameResult(gameResults);
    }

    private BlackJackGame readyGame() {
        outputView.printNamePrompt();
        List<String> playerNames = inputView.readNames();
        return BlackJackGame.ready(playerNames, strategy);
    }

    private void playDealerTurn(BlackJackGame game) {
        while (game.doDealerHitOrStandProcess()) {
            outputView.printDealerAddCardNotice();
        }
    }

    private void playPlayersTurn(BlackJackGame game) {
        while (game.whoseTurn().isPresent()) {
            Player currentPlayer = game.whoseTurn().get();

            if (currentPlayer.isFinished()) {
                handlePlayerStandProcess(game);
                continue;
            }

            outputView.printHitOrStandPrompt(currentPlayer.getName());
            boolean wantToHit = retry(inputView::wantToHit);
            doHitOrStand(wantToHit, game);
        }
    }

    private void doHitOrStand(boolean wantToHit, BlackJackGame game) {
        if (wantToHit) {
            handlePlayerHitProcess(game);
        } else {
            handlePlayerStandProcess(game);
        }
    }

    private void handlePlayerStandProcess(BlackJackGame game) {
        ParticipantDto playerState = game.doStandProcess();
        if (playerState.cards().size() == 2) {
            outputView.printUserState(playerState);
        }
    }

    private void handlePlayerHitProcess(BlackJackGame game) {
        ParticipantDto playerState = game.doHitProcess();
        outputView.printUserState(playerState);
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

