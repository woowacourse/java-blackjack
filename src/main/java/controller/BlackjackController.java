package controller;

import dto.CardDto;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import model.Judge;
import model.UserInput;
import model.bettingamount.BettingAmount;
import model.bettingamount.BettingAmounts;
import model.card.Cards;
import model.deck.Deck;
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
        List<String> names = inputView.readPlayerNames();
        BettingAmounts bettingAmounts = initializeBettingAmounts(names);
        outputView.printNewLine();
        Players players = Players.createByNames(names, deck, bettingAmounts);
        Dealer dealer = new Dealer(deck);

        printInitialGameState(players, dealer);
        playPlayerTurns(players);
        printDealerDrawCount(dealer);
        printFinalGameState(dealer, players);
        printProfit(players, dealer, bettingAmounts);
    }

    private BettingAmounts initializeBettingAmounts(final List<String> names) {
        return new BettingAmounts(names.stream()
                .collect(Collectors.toMap(
                                Function.identity(),
                                name -> new BettingAmount(inputView.readBettingAmount(name))
                        )
                )
        );
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
        while (playerCanDraw(player) && wantsAdditionalCard(player)) {
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

    private GameResults calculateGameResults(final Players players, final Dealer dealer) {
        return new GameResults(players.getPlayers().stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        player -> judge.determineGameResult(dealer.getCards(), player.getCards())
                )));
    }

    private List<CardDto> getCardDtos(final Cards cards) {
        return cards.getCards().stream().map(CardDto::from).toList();
    }

    private void printProfit(final Players players, final Dealer dealer, final BettingAmounts bettingAmounts) {
        outputView.printGameResultHeader();
        GameResults gameResults = calculateGameResults(players, dealer);
        outputView.printProfitWithName("딜러", gameResults.calculateDealerProfit(players, bettingAmounts));
        players.getNames().forEach(name ->
                outputView.printProfitWithName(
                        name,
                        bettingAmounts.findByName(name).getProfitByGameResult(gameResults.getGameResultByName(name)))
        );
    }
}
