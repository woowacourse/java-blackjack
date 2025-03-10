package controller;

import dto.CardDto;
import java.util.ArrayList;
import java.util.List;
import model.BlackjackGame;
import model.Players;
import model.cards.Cards;
import model.cards.DealerCards;
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
        BlackjackGame blackjackGame = BlackjackGame.getBlackjackGame(inputView.readPlayerNames());

        printPlayersAndInitialCards(blackjackGame.getPlayers(), blackjackGame.getDealerCards());
        askToAllPlayersForAdditionalCard(blackjackGame);
        printDealerDraw(blackjackGame);
        printFinalCards(blackjackGame);
        printGameResult(blackjackGame);
    }

    private void printPlayersAndInitialCards(final Players players, final DealerCards dealerCards) {
        outputView.printNewLine();
        outputView.printPlayers(new ArrayList<>(players.getNames()));
        printInitialCardsWithName(dealerCards, players);
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
            UserInput decision = UserInput.from(inputView.askForAdditionalCard(name));
            if (decision.equals(UserInput.NO)) {
                break;
            }
            if (decision.equals(UserInput.YES)) {
                blackjackGame.giveCardToPlayer(name);
                outputView.printCardsWithName(name, getCardDtos(blackjackGame.getPlayerCardsByName(name)));
            }
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
