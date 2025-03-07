package controller;

import dto.CardDto;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.Cards;
import model.DealerCardsFactory;
import model.Deck;
import model.GameResult;
import model.GameResults;
import model.Judge;
import model.PlayerCardsFactory;
import model.Players;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final Deck deck;
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final Deck deck, final InputView inputView, final OutputView outputView) {
        this.deck = deck;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        deck.shuffle();

        List<String> names = inputView.readPlayerNames();

        Players players = generatePlayers(names);
        Cards dealerCards = DealerCardsFactory.generate(deck);

        outputView.printNewLine();
        outputView.printPlayers(names);
        Set<String> sequencedNames = players.getNames();

        outputView.printDealerFirstCard(CardDto.from(dealerCards.getFirstCard()));

        for (String name : sequencedNames) {
            List<CardDto> playerCardDtos = getCardDtos(players.findCardsByName(name));
            outputView.printCardsWithName(name, playerCardDtos);
        }

        outputView.printNewLine();

        for (String name : sequencedNames) {
            while (true) {
                if (players.checkIsBustByName(name)) {
                    break;
                }
                String decision = inputView.askForAdditionalCard(name);
                if (decision.equals("n")) {
                    break;
                }
                if (decision.equals("y")) {
                    players.addCardByName(name, deck.getCard());
                    Cards playerCards = players.findCardsByName(name);
                    outputView.printCardsWithName(name, getCardDtos(playerCards));
                }
            }
        }
        outputView.printNewLine();

        for (int count = 0; count < dealerCards.getAdditionalDrawCount(); count++) {
            outputView.printDealerDrawn();
        }
        outputView.printNewLine();

        outputView.printCardsWithNameAndResult("딜러", getCardDtos(dealerCards), dealerCards.calculateResult());
        for (final String name : sequencedNames) {
            outputView.printCardsWithNameAndResult(
                    name,
                    getCardDtos(players.findCardsByName(name)),
                    players.getResultByName(name)
            );
        }

        Judge judge = new Judge();
        Map<String, GameResult> rawGameResults = new HashMap<>();

        for (String name : sequencedNames) {
            GameResult result = judge.determineGameResult(dealerCards, players.findCardsByName(name));
            rawGameResults.put(name, result);
        }
        GameResults gameResults = new GameResults(rawGameResults);

        outputView.printNewLine();
        outputView.printGameResultHeader();
        outputView.printDealerGameResult(
                gameResults.calculateDealerResultCount(GameResult.WIN),
                gameResults.calculateDealerResultCount(GameResult.DRAW),
                gameResults.calculateDealerResultCount(GameResult.LOSE)
        );

        for (final String name : sequencedNames) {
            outputView.printPlayerGameResult(name, gameResults.getGameResultByName(name).getName());
        }
    }

    private List<CardDto> getCardDtos(final Cards cards) {
        return cards.getCards().stream()
                .map(CardDto::from)
                .toList();
    }

    private Players generatePlayers(final List<String> names) {
        Map<String, Cards> rawPlayers = new LinkedHashMap<>(names.size());
        names.forEach(name -> rawPlayers.put(name, PlayerCardsFactory.generate(deck)));
        return new Players(rawPlayers);
    }

}
