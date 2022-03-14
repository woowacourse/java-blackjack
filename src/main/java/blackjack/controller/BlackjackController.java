package blackjack.controller;

import java.util.stream.Collectors;

import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.GameResult;
import blackjack.domain.game.TurnManager;
import blackjack.domain.game.winningstrategy.BlackjackWinningStrategy;
import blackjack.domain.game.winningstrategy.FinalWinningStrategy;
import blackjack.domain.game.winningstrategy.PlayingWinningStrategy;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void run() {
        Participants participants = createParticipants();
        BlackjackGame blackjackGame = new BlackjackGame(participants);
        GameResult gameResult = new GameResult(participants);

        dealInitialCards(blackjackGame, gameResult);
        dealMoreCards(blackjackGame, gameResult);
        printResult(blackjackGame.getParticipants(), gameResult);
    }

    private Participants createParticipants() {
        return new Participants(InputView.inputPlayerName().stream()
            .map(Player::new)
            .collect(Collectors.toList()));
    }

    private void dealInitialCards(BlackjackGame blackjackGame, GameResult gameResult) {
        blackjackGame.initCards();
        gameResult.update(new BlackjackWinningStrategy());
        OutputView.printInitialCardInformation(blackjackGame.getParticipants());
    }

    private void dealMoreCards(BlackjackGame blackjackGame, GameResult gameResult) {
        dealMoreCardsToPlayers(blackjackGame, gameResult);
        gameResult.update(new PlayingWinningStrategy());
        dealMoreCardsToDealer(blackjackGame);
        gameResult.update(new FinalWinningStrategy());
    }

    private void dealMoreCardsToPlayers(BlackjackGame blackjackGame, GameResult gameResult) {
        TurnManager turnManager = new TurnManager(blackjackGame.getParticipants().getPlayers(),
            gameResult.isDealerBlackjack());
        while (!turnManager.isEndAllTurn()) {
            dealMoreCardsToPlayer(blackjackGame, turnManager);
            turnManager.turnToNext();
        }
    }

    private void dealMoreCardsToPlayer(BlackjackGame blackjackGame, TurnManager turnManager) {
        boolean printCheck = false;
        while (checkCanHitAndInputHit(turnManager)) {
            blackjackGame.playPlayerTurn(turnManager);
            OutputView.printPlayerCardInformation(turnManager.getCurrentPlayer());
            printCheck = true;
        }
        if (!printCheck) {
            OutputView.printPlayerCardInformation(turnManager.getCurrentPlayer());
        }
    }

    private boolean checkCanHitAndInputHit(TurnManager turnManager) {
        return turnManager.isCurrentPlayerCanHit()
            && InputView.inputPlayerHit(turnManager.getCurrentPlayerName());
    }

    private void dealMoreCardsToDealer(BlackjackGame blackjackGame) {
        int count = blackjackGame.playDealerTurnAndReturnTurnCount();
        while (count-- > 0) {
            OutputView.printDealerHitMessage();
        }
    }

    private void printResult(Participants participants, GameResult gameResult) {
        OutputView.printCardsAndPoint(participants);
        OutputView.printResult(gameResult.getDealerResult(), gameResult.getPlayerResult());
    }
}

