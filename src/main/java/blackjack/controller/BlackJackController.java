package blackjack.controller;

import static blackjack.util.Repeater.repeatUntilNoException;

import blackjack.domain.BlackJackGame;
import blackjack.domain.Dealer;
import blackjack.domain.DeckFactory;
import blackjack.domain.Players;
import blackjack.response.DealerScoreResponse;
import blackjack.response.FinalResultResponse;
import blackjack.response.InitialCardResponse;
import blackjack.response.PlayerCardsResponse;
import blackjack.response.PlayersCardsResponse;
import blackjack.view.DrawCommand;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play(final DeckFactory deckFactory) {
        final BlackJackGame blackJackGame = generateBlackJackGame(deckFactory);
        distributeInitialCard(blackJackGame);
        printInitialCards(blackJackGame);
        drawPlayersCards(blackJackGame);
        drawDealerCards(blackJackGame);
        calculateResult(blackJackGame);
        printFinalResult(blackJackGame);
    }


    private BlackJackGame generateBlackJackGame(final DeckFactory deckFactory) {
        return new BlackJackGame(createPlayers(), deckFactory);
    }

    private Players createPlayers() {
        return repeatUntilNoException(
                () -> Players.from(inputView.inputPlayerNames()), outputView::printError);
    }

    private void distributeInitialCard(final BlackJackGame blackJackGame) {
        blackJackGame.distributeInitialCard();
    }

    private void printInitialCards(final BlackJackGame blackJackGame) {
        final InitialCardResponse initialCardResponse = InitialCardResponse.of(
                blackJackGame.getPlayers(),
                blackJackGame.getDealer());
        outputView.printInitialCards(initialCardResponse);
    }

    private void drawPlayersCards(final BlackJackGame blackJackGame) {
        final Players players = blackJackGame.getPlayers();
        for (final String playerName : players.getPlayerNames()) {
            drawPlayerCard(playerName, blackJackGame);
        }
    }

    private void drawPlayerCard(final String playerName, final BlackJackGame blackJackGame) {
        DrawCommand playerInput = DrawCommand.DRAW;
        while (blackJackGame.isPlayerDrawable(playerName) && playerInput != DrawCommand.STAY) {
            playerInput = repeatUntilNoException(
                    () -> inputView.inputCommand(playerName), outputView::printError);
            drawCard(playerName, blackJackGame, playerInput);
            printPlayerResult(playerName, blackJackGame);
        }
    }

    private void drawCard(final String playerName, final BlackJackGame blackJackGame,
            final DrawCommand playerInput) {
        if (playerInput == DrawCommand.DRAW) {
            blackJackGame.drawPlayerCard(playerName);
        }
    }

    private void printPlayerResult(final String playerName, final BlackJackGame blackJackGame) {
        final PlayerCardsResponse playerCardsResponse = PlayerCardsResponse.of(
                playerName,
                blackJackGame.findPlayerByName(playerName)
        );
        outputView.printCardStatusOfPlayer(playerCardsResponse);
    }

    private void drawDealerCards(final BlackJackGame blackJackGame) {
        while (blackJackGame.isDealerDrawable()) {
            blackJackGame.drawDealerCard();
            outputView.printDealerCardDrawMessage();
        }
    }

    private void calculateResult(final BlackJackGame blackJackGame) {
        blackJackGame.calculateFinalResult();
    }

    private void printFinalResult(final BlackJackGame blackJackGame) {
        final Players players = blackJackGame.getPlayers();
        final Dealer dealer = blackJackGame.getDealer();
        printResult(players, dealer);
    }

    private void printResult(final Players players, final Dealer dealer) {
        outputView.printFinalStatusOfDealer(DealerScoreResponse.from(dealer));
        outputView.printFinalStatusOfPlayers(PlayersCardsResponse.from(players));
        outputView.printFinalResult(FinalResultResponse.from(dealer.getResult()));
    }
}
