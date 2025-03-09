package controller;

import dto.CardDto;
import java.util.List;
import java.util.stream.Collectors;
import model.Judge;
import model.UserInput;
import model.card.Cards;
import model.deck.Deck;
import model.gameresult.GameResult;
import model.gameresult.GameResults;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;
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
        Players players = initializePlayers();
        Dealer dealer = new Dealer(deck);

        printInitialGameState(players, dealer);
        playPlayerTurns(players);
        printDealerDrawCount(dealer);
        printFinalGameState(dealer, players);
        printGameResult(players, dealer);
    }

    private Players initializePlayers() {
        List<String> names = inputView.readPlayerNames();
        outputView.printNewLine();
        return Players.createByNames(names, deck);
    }

    private void printInitialGameState(final Players players, final Dealer dealer) {
        outputView.printPlayers(players.getNames());
        outputView.printDealerFirstCard(CardDto.from(dealer.getFirstCard()));
        players.getPlayers().forEach(player ->
                outputView.printCardsWithName(player.getName(), getCardDtos(player.getCards()))
        );
        outputView.printNewLine();
    }

    private void playPlayerTurns(final Players players) {
        players.getPlayers().forEach(this::handlePlayerTurn);
        outputView.printNewLine();
    }

    private void handlePlayerTurn(final Player player) {
        while (playerCanDraw(player)) {
            if (!wantsAdditionalCard(player)) {
                return;
            }
            player.receiveCard(deck.getCard());
            outputView.printCardsWithName(player.getName(), getCardDtos(player.getCards()));
        }
    }

    private boolean playerCanDraw(final Player player) {
        return player.isNotBust();
    }

    private boolean wantsAdditionalCard(final Player player) {
        return UserInput.from(inputView.askForAdditionalCard(player.getName())).equals(UserInput.YES);
    }

    private void printDealerDrawCount(final Dealer dealer) {
        for (int count = 0; count < dealer.getAdditionalDrawCount(); count++) {
            outputView.printDealerDrawn();
        }
    }

    private void printFinalGameState(final Dealer dealer, final Players players) {
        outputView.printCardsWithNameAndResult("딜러", getCardDtos(dealer.getCards()), dealer.calculateScore());
        players.getPlayers().forEach(player ->
                outputView.printCardsWithNameAndResult(
                        player.getName(),
                        getCardDtos(player.getCards()),
                        player.calculateScore())
        );
        outputView.printNewLine();
    }

    private void printGameResult(final Players players, final Dealer dealer) {
        GameResults gameResults = calculateGameResults(players, dealer);

        outputView.printGameResultHeader();
        outputView.printDealerGameResult(
                gameResults.calculateDealerResultCount(GameResult.WIN),
                gameResults.calculateDealerResultCount(GameResult.DRAW),
                gameResults.calculateDealerResultCount(GameResult.LOSE)
        );

        players.getPlayers().forEach(player ->
                outputView.printPlayerGameResult(
                        player.getName(),
                        gameResults.getGameResultByName(player.getName()).getMessage())
        );
    }

    private GameResults calculateGameResults(final Players players, final Dealer dealer) {
        return new GameResults(players.getPlayers().stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        player -> judge.determineGameResult(dealer.getCards(), player.getCards())
                )));
    }

    private List<CardDto> getCardDtos(Cards cards) {
        return cards.getCards().stream().map(CardDto::from).toList();
    }
}
