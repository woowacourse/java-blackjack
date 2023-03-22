package blackjack.controller;

import blackjack.domain.DeckFactory;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.participants.BettingMoney;
import blackjack.domain.participants.Money;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Map;
import java.util.stream.IntStream;

import static blackjack.controller.Repeater.repeatUntilNoException;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void generate(final DeckFactory deckFactory) {
        final BlackjackGame blackjackGame = createBlackJackGame(deckFactory);

        startGame(blackjackGame);
        printInitialCard(blackjackGame);
        playGame(blackjackGame);
        printResult(blackjackGame);
    }

    private BlackjackGame createBlackJackGame(final DeckFactory deckFactory) {
        return repeatUntilNoException(
                () -> BlackjackGame.of(inputView.inputPlayerNames(), deckFactory.generate()), outputView::printError);
    }

    private void startGame(final BlackjackGame blackjackGame) {
        makePlayersPlaceWagers(blackjackGame);
        blackjackGame.distributeInitialCards();
    }

    private void makePlayersPlaceWagers(final BlackjackGame blackjackGame) {
        for (final String playerName : blackjackGame.findPlayerNames()) {
            blackjackGame.placePlayerBets(playerName, inputBettingMoney(playerName));
        }
    }

    private BettingMoney inputBettingMoney(final String playerName) {
        return repeatUntilNoException(
                () -> new BettingMoney(inputView.inputBettingMoney(playerName)), outputView::printError);
    }

    private void printInitialCard(final BlackjackGame blackjackGame) {
        outputView.printInitialCardsGuideMessage(blackjackGame.findPlayerNames());
        outputView.printInitialCardsOfDealer(blackjackGame.findDealerInitialCard());
        for(final String playerName : blackjackGame.findPlayerNames()) {
            outputView.printInitialCardsOfPlayers(playerName, blackjackGame.findCardsOfPlayerByName(playerName));
        }
    }

    private void playGame(final BlackjackGame blackjackGame) {
        drawPlayersCards(blackjackGame);
        drawDealerCards(blackjackGame);
    }

    private void drawPlayersCards(final BlackjackGame blackjackGame) {
        for (final String playerName : blackjackGame.findPlayerNames()) {
            drawPlayerCardByName(playerName, blackjackGame);
        }
    }

    private DrawCommand inputDrawCommand(final String playerName) {
        return repeatUntilNoException(
                () -> DrawCommand.from(inputView.inputCommand(playerName)), outputView::printError);
    }

    private void drawPlayerCardByName(final String playerName, final BlackjackGame blackjackGame) {
        while (blackjackGame.isPlayerDrawable(playerName)) {
            final DrawCommand drawCommand = inputDrawCommand(playerName);
            if(drawCommand == DrawCommand.DRAW) {
                blackjackGame.drawCardOfPlayerByName(playerName);
                outputView.printCurrentCardsOfPlayer(playerName, blackjackGame.findCardsOfPlayerByName(playerName));
            }
            if(drawCommand == DrawCommand.STAY) {
                blackjackGame.stayCardOfPlayerByName(playerName);
            }
        }
    }

    private void drawDealerCards(final BlackjackGame blackjackGame) {
        IntStream.range(0, blackjackGame.findDealerDrawCount())
                .forEach(ignored -> outputView.printDealerCardDrawMessage(blackjackGame.findDealerDrawPoint()));
    }

    private void printResult(final BlackjackGame blackjackGame) {
        printFinalStatusOfParticipants(blackjackGame);
        getPrintProfitOfGameParticipants(blackjackGame);
    }

    private void printFinalStatusOfParticipants(final BlackjackGame blackjackGame) {
        outputView.printFinalStatusOfDealer(blackjackGame.findDealerCard(), blackjackGame.findDealerScore()
                .getValue());

        for (final String playerName : blackjackGame.findPlayerNames()) {
            outputView.printFinalStatusOfPlayer(playerName,
                    blackjackGame.findCardsOfPlayerByName(playerName),
                    blackjackGame.findScoreOfPlayerByName(playerName)
                            .getValue());
        }
    }

    private void getPrintProfitOfGameParticipants(final BlackjackGame blackjackGame) {
        final Map<String, Money> revenueOfPlayers = blackjackGame.findRevenueOfPlayers();
        final Money revenueOfDealer = blackjackGame.findRevenueOfDealer();
        outputView.printProfitOfGameParticipants(revenueOfDealer, revenueOfPlayers);
    }

}
