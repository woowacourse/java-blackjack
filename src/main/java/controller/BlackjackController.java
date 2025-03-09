package controller;

import dto.CardDto;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import model.Cards;
import model.DealerCardsFactory;
import model.Deck;
import model.GameResult;
import model.GameResults;
import model.Judge;
import model.PlayerCardsFactory;
import model.Players;
import model.UserInput;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final Deck deck;
    private final Judge judge;
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final Deck deck, final Judge judge, final InputView inputView,
                               final OutputView outputView) {
        this.deck = deck;
        this.judge = judge;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        deck.shuffle();

        Players players = generatePlayersWithCards(inputView.readPlayerNames());
        Cards dealerCards = DealerCardsFactory.generate(deck);
        outputView.printNewLine();

        printPlayersAndInitialCards(players, dealerCards);

        players.getNames().forEach(name -> askForAdditionalCardByName(name, players));
        outputView.printNewLine();

        for (int count = 0; count < dealerCards.getAdditionalDrawCount(); count++) {
            outputView.printDealerDrawn();
        }
        outputView.printNewLine();

        outputView.printCardsWithNameAndResult("딜러", getCardDtos(dealerCards), dealerCards.calculateResult());
        players.getNames().forEach(name -> outputView.printCardsWithNameAndResult(
                name,
                getCardDtos(players.findCardsByName(name)),
                players.getResultByName(name)
        ));

        GameResults gameResults = calculateGameResults(players, dealerCards);

        outputView.printNewLine();
        printGameResult(gameResults, players);
    }

    private void printGameResult(final GameResults gameResults, final Players players) {
        outputView.printGameResultHeader();
        outputView.printDealerGameResult(
                gameResults.calculateDealerResultCount(GameResult.WIN),
                gameResults.calculateDealerResultCount(GameResult.DRAW),
                gameResults.calculateDealerResultCount(GameResult.LOSE)
        );

        players.getNames().forEach(name ->
                outputView.printPlayerGameResult(name, gameResults.getGameResultByName(name).getName()));
    }

    private GameResults calculateGameResults(final Players players, final Cards dealerCards) {
        return new GameResults(players.getNames().stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        name -> judge.determineGameResult(dealerCards, players.findCardsByName(name)
                        )))
        );
    }

    private void printPlayersAndInitialCards(final Players players, final Cards dealerCards) {
        outputView.printPlayers(new ArrayList<>(players.getNames()));
        printInitialCardsWithName(dealerCards, players);
        outputView.printNewLine();
    }

    private void askForAdditionalCardByName(final String name, final Players players) {
        while (!players.checkIsBustByName(name)) {
            UserInput decision = UserInput.from(inputView.askForAdditionalCard(name));
            if (decision.equals(UserInput.NO)) {
                break;
            }
            if (decision.equals(UserInput.YES)) {
                players.addCardByName(name, deck.getCard());
                Cards playerCards = players.findCardsByName(name);
                outputView.printCardsWithName(name, getCardDtos(playerCards));
            }
        }
    }

    private void printInitialCardsWithName(final Cards dealerCards, final Players players) {
        outputView.printDealerFirstCard(CardDto.from(dealerCards.getFirstCard()));
        for (String name : players.getNames()) {
            List<CardDto> playerCardDtos = getCardDtos(players.findCardsByName(name));
            outputView.printCardsWithName(name, playerCardDtos);
        }
    }

    private List<CardDto> getCardDtos(final Cards cards) {
        return cards.getCards().stream()
                .map(CardDto::from)
                .toList();
    }

    private Players generatePlayersWithCards(final List<String> names) {
        Map<String, Cards> rawPlayers = new LinkedHashMap<>(names.size());
        names.forEach(name -> rawPlayers.put(name, PlayerCardsFactory.generate(deck)));
        return new Players(rawPlayers);
    }
}
