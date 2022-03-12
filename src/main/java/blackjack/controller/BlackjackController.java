package blackjack.controller;

import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.GameResult;
import blackjack.domain.game.TurnManager;
import blackjack.domain.game.WinningResult;
import blackjack.domain.participant.Dealer;
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
        TurnManager gameManager = new TurnManager(blackjackGame.getParticipants().getPlayers());
        while (!gameManager.isAllTurnEnd()) {
            dealMoreCardsToPlayer(gameManager);
        }
    }

    private void dealMoreCardsToPlayer(TurnManager gameManager) {
        boolean printCheck = false;
        while (gameManager.canHitStatus() && gameManager.doPlayerTurn(isHit(gameManager.getCurrentPlayer()))) {
            OutputView.printPlayerCardInformation(gameManager.getCurrentPlayer());
            printCheck = true;
        }
        if (!printCheck) {
            OutputView.printPlayerCardInformation(gameManager.getCurrentPlayer());
        }
        gameManager.turnToNext();
    }

    private boolean isHit(Player player) {
        return InputView.inputPlayerHit(player.getName());
    }

    private void dealMoreCardsToDealer(BlackjackGame blackjackGame) {
        Dealer dealer = blackjackGame.getParticipants().getDealer();
        while (dealer.checkMustHit()) {
            OutputView.printDealerHitMessage();
            blackjackGame.hitCard(dealer);
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

