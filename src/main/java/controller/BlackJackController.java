package controller;

import card.CardDeck;
import cardGame.BlackJackGame;
import controller.dto.WinningResult;
import java.util.List;
import player.Player;
import player.Players;
import player.dto.CardsStatus;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        CardDeck cardDeck = new CardDeck();
        BlackJackGame blackJackGame = new BlackJackGame(cardDeck, cardDeck.firstCardSettings());
        Players players = blackJackGame.initGamePlayer(inputView.inputPlayerNames());

        runBlackJackGame(blackJackGame, players);
        showResult(blackJackGame, players);
    }

    private void runBlackJackGame(BlackJackGame blackJackGame, Players players) {
        outputView.printInitCardStatus(players, blackJackGame.getDealerFirstCard());

        List<CardsStatus> playerResult = blackJackGame.playGame(this::playSingleMatch, players);

        CardsStatus result = blackJackGame.playDealerTurn();
        playerResult.add(result);

        outputView.printExtraCardInfo(result);
        outputView.printPlayStatus(playerResult);
    }

    private void showResult(BlackJackGame blackJackGame, Players players) {
        List<WinningResult> result = blackJackGame.getPlayersResult(players);
        outputView.printDealerResult(blackJackGame.getDealerResult(players));
        outputView.printPlayersResult(result);
    }

    private void playSingleMatch(Player player, CardDeck cardDeck) {
        while (isCanPlayPlayer(player)) {
            player.receiveCard(cardDeck.pickCard());
            outputView.printPlayerCardStatus(player);
        }
    }

    private boolean isCanPlayPlayer(Player player) {
        return !player.isBust() && inputView.inputPlayerCommand(
                player.getName());
    }
}
