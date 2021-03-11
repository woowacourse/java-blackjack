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
    private final BlackJackGame blackJackGame;

    public BlackJackController(BlackJackGame blackJackGame) {
        this.blackJackGame = blackJackGame;
    }

    public void start() {
        try {
            blackJackGame.initialSetting(playersName());
            distributeCards();
            playersTurn(blackJackGame.getPlayers());
            dealerTurn(blackJackGame.getDealer());
            showProfitResult();
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

    private void distributeCards() {
        blackJackGame.distributeCards();
        OutputView.distributeFirstTwoCard(blackJackGame.playersDto(), blackJackGame.dealerDto());
    }

    private void playersTurn(Players players) {
        for (Player player : players.getPlayers()) {
            eachPlayerTurn(player);
        }
    }

    private void eachPlayerTurn(Player player) {
        while (player.canDraw() && askDrawCard(player)) {
            player.draw(blackJackGame.drawOneCard());
            OutputView.showCards(blackJackGame.playerDto(player));
        }
    }

    private boolean askDrawCard(Player player) {
        try {
            OutputView.askOneMoreCard(blackJackGame.playerDto(player));
            boolean wantDraw = InputView.inputAnswer();
            playerWantStopDraw(player, wantDraw);
            return wantDraw;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return askDrawCard(player);
        }
    }

    private void playerWantStopDraw(Player player, boolean wantDraw) {
        if (!wantDraw) {
            OutputView.showCards(blackJackGame.playerDto(player));
            player.stay();
        }
    }

    private void dealerTurn(Dealer dealer) {
        while (dealer.canDraw()) {
            dealer.draw(blackJackGame.drawOneCard());
            OutputView.dealerReceiveOneCard();
        }
        if (dealer.isHit()) {
            dealer.stay();
        }
    }

    private void showProfitResult() {
        OutputView.showAllCards(blackJackGame.playersDto(), blackJackGame.dealerDto());
        OutputView.showFinalProfitResult(blackJackGame.profitResult());
    }
}
