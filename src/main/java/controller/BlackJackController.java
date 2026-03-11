package controller;

import domain.BlackJackGame;
import domain.CardCreationStrategy;
import domain.Player;
import dto.ParticipantDto;
import java.util.List;
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
                game.getDealerGameSettingState(),
                game.getPlayersGameSettingStates()
        );

        playPlayersTurn(game);
        playDealerTurn(game);
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

            outputView.printHitOrStandPrompt(currentPlayer.getName());
            String hitOrStandInfo = retry(inputView::readHitOrStand);
            doHitOrStand(hitOrStandInfo, game);
        }
    }

    private void doHitOrStand(String hitOrStand, BlackJackGame game) {
        if (hitOrStand.equals("y")) {
            handlePlayerHitProcess(game);
            return;
        }
        handlePlayerStandProcess(game);
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

