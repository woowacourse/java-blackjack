package blackjack.controller;

import blackjack.domain.DeckFactory;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.participants.BettingMoney;
import blackjack.domain.participants.Money;
import blackjack.view.DrawCommand;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Map;
import java.util.stream.IntStream;

import static blackjack.controller.Repeater.repeatUntilNoException;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void generate(DeckFactory deckFactory) {
        BlackjackGame blackjackGame = createBlackJackGame(deckFactory);

        startGame(blackjackGame);
        printInitialCard(blackjackGame);
        playGame(blackjackGame);
        printResult(blackjackGame);
    }

    private BlackjackGame createBlackJackGame(DeckFactory deckFactory) {
        return repeatUntilNoException(
                () -> BlackjackGame.of(inputView.inputPlayerNames(), deckFactory.generate()), outputView::printError);
    }

    private void startGame(BlackjackGame blackjackGame) {
        makePlayersPlaceWagers(blackjackGame);
        blackjackGame.distributeInitialCards();
    }

    private void makePlayersPlaceWagers(BlackjackGame blackjackGame) {
        for (String playerName : blackjackGame.findPlayerNames()) {
            blackjackGame.placePlayerBets(playerName, inputBettingMoney(playerName));
        }
    }

    private BettingMoney inputBettingMoney(String playerName) {
        return repeatUntilNoException(
                () -> new BettingMoney(inputView.inputBettingMoney(playerName)), outputView::printError);
    }

    private void printInitialCard(BlackjackGame blackjackGame) {
        outputView.printInitialCards(blackjackGame.findDealerInitialCard(),
                blackjackGame.findPlayerNameToCards());
    }

    private void playGame(BlackjackGame blackjackGame) {
        drawPlayersCards(blackjackGame);
        drawDealerCards(blackjackGame);
    }

    private void drawPlayersCards(BlackjackGame blackjackGame) {
        for (String playerName : blackjackGame.findPlayerNames()) {
            drawPlayerCard(playerName, blackjackGame);
        }
    }

    private DrawCommand inputDrawCommand(String playerName) {
        return repeatUntilNoException(
                () -> inputView.inputCommand(playerName), outputView::printError);
    }

    private void drawPlayerCard(String playerName, BlackjackGame blackjackGame) {
        DrawCommand drawCommand = DrawCommand.DRAW;

        while (blackjackGame.isPlayerDrawable(playerName) && drawCommand == DrawCommand.DRAW) {
            drawCommand = inputDrawCommand(playerName);
            blackjackGame.drawCardOfPlayerByName(playerName, drawCommand);
            outputView.printCurrentCardsOfPlayer(playerName, blackjackGame.findCardsOfPlayerByName(playerName));
        }
    }

    private void drawDealerCards(BlackjackGame blackjackGame) {
        IntStream.range(0, blackjackGame.findDealerDrawCount())
                .forEach(ignored -> outputView.printDealerCardDrawMessage(blackjackGame.findDealerDrawPoint()));
    }

    private void printResult(BlackjackGame blackjackGame) {
        printFinalStatusOfParticipants(blackjackGame);
        getPrintProfitOfGameParticipants(blackjackGame);
    }

    private void printFinalStatusOfParticipants(BlackjackGame blackjackGame) {
        outputView.printFinalStatusOfDealer(blackjackGame.findDealerCard(), blackjackGame.findDealerScore()
                .getValue());

        for (String playerName : blackjackGame.findPlayerNames()) {
            outputView.printFinalStatusOfPlayer(playerName,
                    blackjackGame.findCardsOfPlayerByName(playerName),
                    blackjackGame.findScoreOfPlayerByName(playerName)
                            .getValue());
        }
    }

    private void getPrintProfitOfGameParticipants(BlackjackGame blackjackGame) {
        Map<String, Money> revenueOfPlayers = blackjackGame.findRevenueOfPlayers();
        Money revenueOfDealer = blackjackGame.findRevenueOfDealer();
        outputView.printProfitOfGameParticipants(revenueOfDealer, revenueOfPlayers);
    }

}
