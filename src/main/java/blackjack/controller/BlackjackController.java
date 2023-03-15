package blackjack.controller;

import blackjack.domain.blackjack.BlackjackGame;
import blackjack.domain.card.ShuffledDeckFactory;
import blackjack.domain.participant.Participants;
import blackjack.view.DrawCommand;
import blackjack.view.IOView;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BlackjackController {

    private final IOView ioView;
    private BlackjackGame blackjackGame;

    public BlackjackController(final IOView ioView) {
        this.ioView = ioView;
    }

    public void init() {
        final List<String> playerNames = inputPlayerNames();
        final List<Integer> moneys = new ArrayList<>();
        for (final String playerName : playerNames) {
            moneys.add(inputPlayerMoney(playerName));
        }
        blackjackGame = BlackjackGame.of(playerNames, moneys, new ShuffledDeckFactory().generate());
        blackjackGame.distributeInitialCards();
        ioView.printInitialCards(blackjackGame.getDealerFirstCard(), blackjackGame.getPlayersCards());
    }

    public void play() {
        for (final String playerName : blackjackGame.getPlayerNames()) {
            drawPlayerCards(playerName);
        }
        while (blackjackGame.isDealerDrawable()) {
            blackjackGame.drawDealerCard();
            ioView.printDealerCardDrawMessage();
        }
    }

    public void calculateResult() {
        ioView.printFinalStatusOfDealer(blackjackGame.getDealerScore(), blackjackGame.getDealerCards());
        ioView.printFinalStatusOfPlayers(blackjackGame.getPlayersCards(), blackjackGame.getPlayersScores());
        ioView.printFinalMoney(blackjackGame.calculateMoney());
    }

    private void drawPlayerCards(final String playerName) {
        DrawCommand playerChoice = DrawCommand.DRAW;
        while (blackjackGame.isPlayerDrawable(playerName) && playerChoice != DrawCommand.STAY) {
            playerChoice = inputPlayerChoice(playerName);
            drawPlayerCard(playerName, playerChoice);
            ioView.printCardStatusOfPlayer(playerName, blackjackGame.getPlayerCards(playerName));
        }
    }

    private void drawPlayerCard(final String playerName, final DrawCommand playerChoice) {
        if (playerChoice == DrawCommand.DRAW) {
            blackjackGame.drawPlayerCard(playerName);
        }
    }

    private List<String> inputPlayerNames() {
        return repeatUntilNoException(() -> {
                    final List<String> names = ioView.inputPlayerNames();
                    Participants.validatePlayerNames(names);
                    return names;
                },
                ioView::printError);
    }

    private int inputPlayerMoney(final String playerName) {
        return repeatUntilNoException(() -> {
                    final int amount = ioView.inputPlayerMoney(playerName);
                    Participants.validateBettingMoney(amount);
                    return amount;
                },
                ioView::printError);
    }

    private DrawCommand inputPlayerChoice(final String playerName) {
        return repeatUntilNoException(() -> ioView.inputCommand(playerName), ioView::printError);
    }

    private <T> T repeatUntilNoException(final Supplier<T> supplier,
            final Consumer<Exception> exceptionHandler) {
        while (true) {
            try {
                return supplier.get();
            } catch (final IllegalArgumentException e) {
                exceptionHandler.accept(e);
            }
        }
    }
}
