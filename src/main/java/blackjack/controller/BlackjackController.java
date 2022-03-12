package blackjack.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.WinningResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void run() {
        BlackjackGame blackjackGame = new BlackjackGame(createParticipants());
        blackjackGame.initCards();
        OutputView.printInitialCardInformation(blackjackGame.getParticipants());
        dealMoreCards(blackjackGame);
        printResult(blackjackGame.getParticipants());
    }

    private Participants createParticipants() {
        List<String> playerNames = InputView.inputPlayerName();
        return new Participants(playerNames.stream()
            .map(Player::new)
            .collect(Collectors.toList()));
    }

    private void dealMoreCards(BlackjackGame blackjackGame) {
        dealMoreCardsToPlayers(blackjackGame);
        dealMoreCardsToDealer(blackjackGame);
    }

    private void dealMoreCardsToPlayers(BlackjackGame blackjackGame) {
        for (Player player : blackjackGame.getParticipants().getPlayers()) {
            dealMoreCardsToPlayer(blackjackGame, player);
        }
    }

    private void dealMoreCardsToPlayer(BlackjackGame blackjackGame, Player player) {
        boolean printCheck = false;
        while (player.canHit() && blackjackGame.isHitAndDealMoreCard(isHit(player), player)) {
            OutputView.printPlayerCardInformation(player);
            printCheck = true;
        }
        if (!printCheck) {
            OutputView.printPlayerCardInformation(player);
        }
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

        BlackjackGame blackjackGame = new BlackjackGame(participants);
        blackjackGame.calculatePlayerResult();

        Map<WinningResult, Integer> dealerResult = blackjackGame.getDealerResult();
        Map<Player, WinningResult> playerResult = blackjackGame.getPlayerResult();

        OutputView.printResult(dealerResult, playerResult);
    }
}

