package controller;

import dto.CardDto;
import java.util.ArrayList;
import java.util.List;
import model.UserInput;
import model.bettingamount.BettingAmount;
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
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final Deck deck, final InputView inputView,
                               final OutputView outputView) {
        this.deck = deck;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        deck.shuffle();
        List<String> names = inputView.readPlayerNames();
        Players players = initializePlayers(names);
        Dealer dealer = new Dealer(deck);
        startGame(players, dealer);
        endGame(players, dealer);
    }

    private Players initializePlayers(final List<String> names) {
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            BettingAmount bettingAmount = new BettingAmount(inputView.readBettingAmount(name));
            Player player = new Player(name, deck, bettingAmount);
            players.add(player);
        }
        return new Players(players);
    }

    private void startGame(final Players players, final Dealer dealer) {
        outputView.printNewLine();
        printInitialGameState(players, dealer);
        playPlayerTurns(players);
        printDealerDrawCount(dealer);
    }

    private void endGame(final Players players, final Dealer dealer) {
        printFinalGameState(dealer, players);
        printProfit(players, dealer);
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
        outputView.printDealerDrawn(dealer.getAdditionalDrawCount());
    }

    private void printFinalGameState(final Dealer dealer, final Players players) {
        outputView.printCardsWithNameAndResult("딜러", getCardDtos(dealer.getCards()), dealer.calculateScore());
        for (Player player : players.getPlayers()) {
            outputView.printCardsWithNameAndResult(
                    player.getName(),
                    getCardDtos(player.getCards()),
                    player.calculateScore());
        }
        outputView.printNewLine();
    }

    private List<CardDto> getCardDtos(final Cards cards) {
        return cards.getCards()
                .stream()
                .map(CardDto::from)
                .toList();
    }

    private void printProfit(final Players players, final Dealer dealer) {
        outputView.printGameResultHeader();
        GameResults gameResults = GameResults.createFromParticipants(players, dealer);
        int dealerProfit = gameResults.calculateDealerProfit(players);
        outputView.printProfitWithName("딜러", dealerProfit);
        for (Player player : players.getPlayers()) {
            outputView.printProfitWithName(player.getName(), gameResults.calculatePlayerProfit(player));
        }
    }
}
