package controller;

import dto.CardDto;
import exception.IllegalBlackjackInputException;
import exception.IllegalBlackjackStateException;
import java.util.List;
import model.BlackjackGame;
import model.bet.BettingResults;
import model.cards.Cards;
import model.cards.DealerCards;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        try {
            BlackjackGame blackjackGame = getBlackjackGame();
            printPlayersAndInitialCards(blackjackGame);
            askToAllPlayersForAdditionalCard(blackjackGame);
            printDealerDraw(blackjackGame);
            printFinalCards(blackjackGame);
            printBettingResult(blackjackGame);
        } catch (IllegalBlackjackStateException e) {
            outputView.printExceptionMessage(e);
        } catch (Exception e) {
            outputView.informUnexpectedException();
        }
    }

    private BlackjackGame getBlackjackGame() {
        List<String> playerNames = getPlayerNames();
        List<Integer> playersBet = getPlayersBet(playerNames);

        return BlackjackGame.createBlackjackGame(
                playerNames,
                playersBet
        );
    }

    private List<String> getPlayerNames() {
        try {
            return inputView.readPlayerNames();
        } catch (IllegalBlackjackInputException e) {
            outputView.printExceptionMessage(e);
            return getPlayerNames();
        }
    }

    private List<Integer> getPlayersBet(final List<String> playerNames) {
        return playerNames.stream()
                .map(this::getPlayerBet)
                .toList();
    }

    private int getPlayerBet(final String name) {
        try {
            outputView.printNewLine();
            return inputView.readPlayersBet(name);
        } catch (IllegalBlackjackInputException e) {
            outputView.printExceptionMessage(e);
            return getPlayerBet(name);
        }
    }

    private void printPlayersAndInitialCards(final BlackjackGame blackjackGame) {
        outputView.printNewLine();
        outputView.printPlayers(blackjackGame.getSequencedPlayerNames());
        printInitialCardsWithName(blackjackGame);
        outputView.printNewLine();
    }

    private void printInitialCardsWithName(final BlackjackGame blackjackGame) {
        DealerCards dealerCards = blackjackGame.getDealerCards();
        outputView.printDealerFirstCard(CardDto.from(dealerCards.getFirstCard()));
        for (String name : blackjackGame.getSequencedPlayerNames()) {
            List<CardDto> playerCardDtos = getCardDtos(blackjackGame.getPlayerCardsByName(name));
            outputView.printCardsWithName(name, playerCardDtos);
        }
    }

    private void askToAllPlayersForAdditionalCard(final BlackjackGame blackjackGame) {
        blackjackGame.getSequencedPlayerNames()
                .forEach(name -> askForAdditionalCardByName(name, blackjackGame));
    }

    private void askForAdditionalCardByName(final String name, final BlackjackGame blackjackGame) {
        while (blackjackGame.canPlayerDrawCard(name)) {
            UserInput decision = getUserInput(name);
            if (decision == UserInput.NO) {
                break;
            }
            if (decision == UserInput.YES) {
                blackjackGame.giveCardToPlayer(name);
                outputView.printCardsWithName(name, getCardDtos(blackjackGame.getPlayerCardsByName(name)));
            }
        }
    }

    private UserInput getUserInput(final String name) {
        try {
            return UserInput.from(inputView.askForAdditionalCard(name));
        } catch (IllegalBlackjackInputException e) {
            outputView.printExceptionMessage(e);
            return getUserInput(name);
        }
    }

    private void printDealerDraw(final BlackjackGame blackjackGame) {
        outputView.printNewLine();
        for (int count = 0; count < blackjackGame.getDealerAdditionalDrawCount(); count++) {
            outputView.printDealerDrawn();
        }
    }

    private void printFinalCards(final BlackjackGame blackjackGame) {
        outputView.printNewLine();
        outputView.printDealerCardsWithResult(
                getCardDtos(blackjackGame.getDealerCards()),
                blackjackGame.getDealerResult()
        );

        blackjackGame.getSequencedPlayerNames().forEach(name -> outputView.printCardsWithNameAndResult(
                name,
                getCardDtos(blackjackGame.getPlayerCardsByName(name)),
                blackjackGame.getPlayerResultByName(name)
        ));
    }

    private void printBettingResult(final BlackjackGame blackjackGame) {
        outputView.printNewLine();
        BettingResults bettingResults = blackjackGame.calculateBettingResults();
        outputView.printGameResultHeader();
        outputView.printDealerBettingResult(bettingResults.calculateDealerBettingResult());

        blackjackGame.getSequencedPlayerNames().forEach(name ->
                outputView.printPlayerBettingResult(name, bettingResults.getBettingResultByName(name)));
    }

    private List<CardDto> getCardDtos(final Cards cards) {
        return cards.getCards().stream()
                .map(CardDto::from)
                .toList();
    }
}
