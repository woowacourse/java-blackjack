package blackjack.controller;

import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.GameResult;
import blackjack.domain.game.TurnManager;
import blackjack.domain.game.WinningResult;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void run() {
        BlackjackGame blackjackGame = new BlackjackGame(createParticipants());
        dealInitialCards(blackjackGame);
        dealMoreCards(blackjackGame);
        printResult(blackjackGame.getParticipants());
    }

    private Participants createParticipants() {
        return new Participants(InputView.inputPlayerName().stream()
            .map(Player::new)
            .collect(Collectors.toList()));
    }

    private void dealInitialCards(BlackjackGame blackjackGame) {
        blackjackGame.initCards();
        OutputView.printInitialCardInformation(blackjackGame.getParticipants());
    }

    private void dealMoreCards(BlackjackGame blackjackGame) {
        dealMoreCardsToPlayers(blackjackGame);
        dealMoreCardsToDealer(blackjackGame);
    }

    private void dealMoreCardsToPlayers(BlackjackGame blackjackGame) {
        TurnManager turnManager = new TurnManager(blackjackGame.getParticipants().getPlayers());
        while (!turnManager.isEndAllTurn()) {
            dealMoreCardsToPlayer(blackjackGame, turnManager);
            turnManager.turnToNext();
        }
    }

    private void dealMoreCardsToPlayer(BlackjackGame blackjackGame, TurnManager turnManager) {
        boolean printCheck = false;
        while (canHitAndIsHit(turnManager)) {
            blackjackGame.playPlayerTurn(turnManager);
            OutputView.printPlayerCardInformation(turnManager.getCurrentPlayer());
            printCheck = true;
        }
        if (!printCheck) {
            OutputView.printPlayerCardInformation(turnManager.getCurrentPlayer());
        }
    }

    private boolean canHitAndIsHit(TurnManager turnManager) {
        Player currentPlayer = turnManager.getCurrentPlayer();
        return currentPlayer.canHit() && InputView.inputPlayerHit(currentPlayer.getName());
    }

    private void dealMoreCardsToDealer(BlackjackGame blackjackGame) {
        int count = blackjackGame.playDealerTurnAndReturnTurnCount();
        while (count-- > 0) {
            OutputView.printDealerHitMessage();
        }
    }

    private void printResult(Participants participants) {
        OutputView.printCardsAndPoint(participants);

        GameResult gameResult = new GameResult(participants);

        Map<WinningResult, Integer> dealerResult = gameResult.getDealerResult();
        Map<Player, WinningResult> playerResult = gameResult.getPlayerResult();

        OutputView.printResult(dealerResult, playerResult);
    }
}

