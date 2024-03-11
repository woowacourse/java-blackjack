package controller;

import card.CardDeck;
import cardGame.BlackJackGame;
import cardGame.SingleMatch;
import controller.dto.SinglePlayerResultDto;
import java.util.List;
import player.Players;
import player.dto.SinglePlayerStatusDto;
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
        BlackJackGame blackJackGame = new BlackJackGame();
        Players players = blackJackGame.initGamePlayer(inputView.inputPlayerNames());

        runBlackJackGame(blackJackGame, players);
        showResult(blackJackGame, players);
    }

    private void runBlackJackGame(BlackJackGame blackJackGame, Players players) {
        outputView.printInitCardStatus(players, blackJackGame.getInitDealerStatus());

        List<SinglePlayerStatusDto> playerResult = blackJackGame.playGame(this::playSingleMatch, players);

        SinglePlayerStatusDto result = blackJackGame.playDealerTurn();
        playerResult.add(result);

        outputView.printExtraCardInfo(result);
        outputView.printPlayStatus(playerResult);
    }

    private void showResult(BlackJackGame blackJackGame, Players players) {
        List<SinglePlayerResultDto> result = blackJackGame.getPlayersResult(players);
        outputView.printDealerResult(blackJackGame.getDealerResult(players));
        outputView.printPlayersResult(result);
    }

    private void playSingleMatch(SingleMatch singleMatch, CardDeck cardDeck) {
        while (isCanPlayPlayer(singleMatch)) {
            singleMatch.getMoreCard(cardDeck.pickCard());
            outputView.printPlayerCardStatus(singleMatch.getPlayer());
        }
    }

    private boolean isCanPlayPlayer(SingleMatch singleMatch) {
        return !singleMatch.isBustPlayer() && inputView.inputPlayerCommand(
                singleMatch.getPlayer().getName());
    }
}
