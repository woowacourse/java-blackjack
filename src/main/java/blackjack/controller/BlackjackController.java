package blackjack.controller;

import blackjack.domain.game.BlackjackGame;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.money.Betting;
import blackjack.domain.result.GameResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.HashMap;
import java.util.Map;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void startGame() {
        BlackjackGame blackjackGame = BlackjackGame.create();
        Dealer dealer = blackjackGame.createDealer();
        Players players = blackjackGame.createPlayers(inputView.readPlayerNames());
        Map<Player, Betting> bettingBoard = bet(players, blackjackGame);

        dealInitCards(dealer, players, blackjackGame);
        receiveAdditionalCard(dealer, players, blackjackGame);
        printResult(dealer, players, blackjackGame);
    }

    private Map<Player, Betting> bet(Players players, BlackjackGame blackjackGame) {
        Map<Player, Betting> bettingBoard = new HashMap<>();

        for (Player player : players.getPlayers()) {
            blackjackGame.updateBettingBoard(bettingBoard, player, inputView.readBettingAmount(player));
        }

        return bettingBoard;
    }

    private void dealInitCards(Dealer dealer, Players players, BlackjackGame blackjackGame) {
        blackjackGame.dealInitCards(dealer, players);
        outputView.printInitCardStatus(dealer, players);
    }

    private void receiveAdditionalCard(Dealer dealer, Players players, BlackjackGame blackjackGame) {
        for (Player player : players.getPlayers()) {
            receivePlayerAdditionalCard(player, blackjackGame);
        }
        receiveDealerAdditionalCard(dealer, blackjackGame);
    }

    private void receivePlayerAdditionalCard(Player player, BlackjackGame blackjackGame) {
        while (blackjackGame.isHittable(player) && isPlayerInputHit(player)) {
            blackjackGame.hit(player);
            outputView.printCardHandStatus(player);
        }
    }

    private boolean isPlayerInputHit(Player player) {
        return inputView.readHitOrStand(player);
    }

    private void receiveDealerAdditionalCard(Dealer dealer, BlackjackGame blackjackGame) {
        while (blackjackGame.isHit(dealer)) {
            blackjackGame.hit(dealer);
            outputView.printDealerHitMessage();
        }
    }

    public void printResult(Dealer dealer, Players players, BlackjackGame blackjackGame) {
        outputView.printTotalCardHandStatus(dealer, players);
        printGameResult(dealer, players, blackjackGame);
    }

    private void printGameResult(Dealer dealer, Players players, BlackjackGame blackjackGame) {
        Map<Player, GameResult> playerResults = blackjackGame.getPlayerResults(players, dealer);
        Map<GameResult, Long> dealerResult = blackjackGame.getDealerResult(playerResults);
        outputView.printGameResult(dealerResult, playerResults);
    }
}
