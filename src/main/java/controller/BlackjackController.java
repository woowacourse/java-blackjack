package controller;

import domain.ProfitResult;
import domain.SettingGame;
import domain.answer.Answer;
import domain.card.CardDeck;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;
import view.InputView;
import view.OutputView;

public class BlackjackController {
    private Dealer dealer;
    private Players players;
    private CardDeck cardDeck;

    public void run() {
        ready();
        startGame();
        result();
    }

    private void ready() {
        SettingGame settingGame = new SettingGame(InputView.inputPlayerName());
        cardDeck = settingGame.getCardDeck();
        OutputView.printSetDrawCard(settingGame.getPlayersName());

        dealer = settingGame.generateDealer();
        players = settingGame.generatePlayers();
        OutputView.userReport(dealer.startCardReport(), players.cardReport());
    }

    private void startGame() {
        for (Player player : players.getPlayers()) {
            playerTurn(player);
        }
        dealerTurn();
    }

    private void playerTurn(Player player) {
        while (player.isDrawCard() && new Answer(InputView.inputAnswer(player.getName())).isYes()) {
            player.drawCard(cardDeck.giveCard());
            OutputView.userReport(player.cardReport());
        }
    }

    private void dealerTurn() {
        if (dealer.isDrawCard()) {
            dealer.drawCard(cardDeck.giveCard());
            OutputView.printDealerAdditionalCard();
        }
    }

    private void result() {
        OutputView.userReport(dealer.userResult(), players.playersResult());

        ProfitResult profitResult = new ProfitResult(players, dealer);
        OutputView.printWinning(profitResult.getWinningUserResult());
    }
}
