package blackjack.controller;

import blackjack.domain.BettingMoney;
import blackjack.domain.BlackJackGame;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackJackController {

    public void start() {
        try {
            BlackJackGame blackJackGame = new BlackJackGame(playersName());
            distributeCards(blackJackGame);
            playersTurn(blackJackGame.getPlayers(), blackJackGame);
            dealerTurn(blackJackGame.getDealer(), blackJackGame);
            showProfitResult(blackJackGame);
        } catch (RuntimeException e) {
            OutputView.printError(e.getMessage());
            start();
        }
    }

    private List<String> playersName() {
        try {
            OutputView.enterPlayersName();
            return InputView.inputName();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return playersName();
        }
    }

    public static void bettingEachPlayer(Player player) {
        try {
            OutputView.askBettingMoney(player);
            player.betting(new BettingMoney(InputView.inputBettingMoney()));
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            bettingEachPlayer(player);
        }
    }

    private void distributeCards(BlackJackGame blackJackGame) {
        blackJackGame.distributeCards();
        OutputView.distributeFirstTwoCard(blackJackGame.playersDto(), blackJackGame.dealerDto());
    }

    private void playersTurn(Players players, BlackJackGame blackJackGame) {
        for (Player player : players.getPlayers()) {
            eachPlayerTurn(player, blackJackGame);
        }
    }

    private void eachPlayerTurn(Player player, BlackJackGame blackJackGame) {
        while (player.canDraw() && askDrawCard(player, blackJackGame)) {
            player.draw(blackJackGame.drawOneCard());
            OutputView.showCards(blackJackGame.playerDto(player));
        }
    }

    private boolean askDrawCard(Player player, BlackJackGame blackJackGame) {
        try {
            OutputView.askOneMoreCard(blackJackGame.playerDto(player));
            boolean wantDraw = InputView.inputAnswer();
            playerWantStopDraw(player, blackJackGame, wantDraw);
            return wantDraw;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return askDrawCard(player, blackJackGame);
        }
    }

    private void playerWantStopDraw(Player player, BlackJackGame blackJackGame, boolean wantDraw) {
        if (!wantDraw) {
            OutputView.showCards(blackJackGame.playerDto(player));
            player.stay();
        }
    }

    private void dealerTurn(Dealer dealer, BlackJackGame blackJackGame) {
        while (dealer.canDraw()) {
            dealer.draw(blackJackGame.drawOneCard());
            OutputView.dealerReceiveOneCard();
        }
        if (dealer.isHit()) {
            dealer.stay();
        }
    }

    private void showProfitResult(BlackJackGame blackJackGame) {
        OutputView.showAllCards(blackJackGame.playersDto(), blackJackGame.dealerDto());
        OutputView.showFinalProfitResult(blackJackGame.profitResult());
    }
}
