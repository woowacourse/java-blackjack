package controller;

import dto.CardDto;
import exception.IllegalBlackjackInputException;
import exception.IllegalBlackjackStateException;
import java.util.List;
import model.BlackjackGame;
import model.Players;
import model.cards.Cards;
import model.cards.DealerCards;
import model.cards.DealerCardsFactory;
import model.cards.PlayerCardsFactory;
import model.deck.DeckFactory;
import model.result.GameResult;
import model.result.GameResults;
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
            printGameResult(blackjackGame);
        } catch (IllegalBlackjackStateException e) {
            outputView.printExceptionMessage(e);
        }
    }

    private BlackjackGame getBlackjackGame() {
        DeckFactory deckFactory = new DeckFactory();
        PlayerCardsFactory playerCardsFactory = new PlayerCardsFactory();
        DealerCardsFactory dealerCardsFactory = new DealerCardsFactory();

        return BlackjackGame.getBlackjackGame(
                getPlayerNames(),
                deckFactory,
                playerCardsFactory,
                dealerCardsFactory
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

    private void printPlayersAndInitialCards(final BlackjackGame blackjackGame) {
        outputView.printNewLine();
        outputView.printPlayers(blackjackGame.getSequencedPlayerNames());
        printInitialCardsWithName(blackjackGame.getDealerCards(), blackjackGame.getPlayers());
        outputView.printNewLine();
    }

    private void printInitialCardsWithName(final DealerCards dealerCards, final Players players) {
        outputView.printDealerFirstCard(CardDto.from(dealerCards.getFirstCard()));
        for (String name : players.getNames()) {
            List<CardDto> playerCardDtos = getCardDtos(players.findCardsByName(name));
            outputView.printCardsWithName(name, playerCardDtos);
        }
    }

    private void askToAllPlayersForAdditionalCard(final BlackjackGame blackjackGame) {
        blackjackGame.getSequencedPlayerNames()
                .forEach(name -> askForAdditionalCardByName(name, blackjackGame));
    }

    private void askForAdditionalCardByName(final String name, final BlackjackGame blackjackGame) {
        while (!blackjackGame.checkIsBustByName(name)) {
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
        outputView.printCardsWithNameAndResult("딜러", getCardDtos(blackjackGame.getDealerCards()),
                blackjackGame.getDealerResult());

        blackjackGame.getSequencedPlayerNames().forEach(name -> outputView.printCardsWithNameAndResult(
                name,
                getCardDtos(blackjackGame.getPlayerCardsByName(name)),
                blackjackGame.getPlayerResultByName(name)
        ));
    }

    private void printGameResult(final BlackjackGame blackjackGame) {
        outputView.printNewLine();
        GameResults gameResults = blackjackGame.calculateGameResults();
        outputView.printGameResultHeader();
        outputView.printDealerGameResult(
                gameResults.calculateDealerResultCount(GameResult.WIN),
                gameResults.calculateDealerResultCount(GameResult.DRAW),
                gameResults.calculateDealerResultCount(GameResult.LOSE)
        );

        blackjackGame.getSequencedPlayerNames().forEach(name ->
                outputView.printPlayerGameResult(name, gameResults.getGameResultByName(name).getName()));
    }

    private List<CardDto> getCardDtos(final Cards cards) {
        return cards.getCards().stream()
                .map(CardDto::from)
                .toList();
    }
}
