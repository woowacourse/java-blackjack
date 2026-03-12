package blackjack.controller;

import blackjack.domain.betting.BettingAmount;
import blackjack.domain.betting.Bettings;
import blackjack.domain.deck.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.dto.ProfitResult;
import blackjack.dto.StateResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private static final int SIZE_OF_INITIAL_CARD = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Players players = readPlayers();
        Dealer dealer = new Dealer();

        Bettings bettings = readBettingAmounts(players);

        setInitialCards(players, dealer);
        printInitialSettings(players, dealer);

        getMoreCardsOfPlayers(players);
        getMoreCardsOfDealer(dealer, players);

        printCardsOfParticipants(players, dealer);
        printResult(bettings, players, dealer);
    }

    private Players readPlayers() {
        List<String> playersName = inputView.readPlayersName();
        return Players.from(playersName);
    }

    private Bettings readBettingAmounts(Players players) {
        Bettings bettings = new Bettings();
        for (Player player : players.getPlayers()) {
            Integer bettingAmount = inputView.readBettingAmount(player.getName());
            bettings.put(player, new BettingAmount(bettingAmount));
        }
        return bettings;
    }

    private void setInitialCards(Players players, Dealer dealer) {
        Deck.shuffle();
        for (int i = 0; i < SIZE_OF_INITIAL_CARD; i++) {
            players.draw();
            dealer.draw(Deck.pop());
        }
    }

    private void printInitialSettings(Players players, Dealer dealer) {
        outputView.printInitialSettingsDoneMessage(dealer.getName(), players.getPlayersName());
        outputView.printCards(dealer.getName(), List.of(dealer.getFirstCardName()));
        for (Player player : players.getPlayers()) {
            outputView.printCards(player.getName(), player.getCardsName());
        }
        outputView.println();
    }

    private void getMoreCardsOfPlayers(Players players) {
        for (Player player : players.getPlayers()) {
            getMoreCardsOfPlayer(player);
        }
    }

    private void getMoreCardsOfPlayer(Player player) {
        boolean isDraw = false;
        while (player.canDraw() && readPlayerWantMoreCard(player)) {
            player.draw(Deck.pop());
            outputView.printCards(player.getName(), player.getCardsName());
            isDraw = true;
        }
        if (!isDraw) {
            outputView.printCards(player.getName(), player.getCardsName());
        }
    }

    private boolean readPlayerWantMoreCard(Player player) {
        return "y".equals(inputView.readMoreCard(player.getName()));
    }

    private void getMoreCardsOfDealer(Dealer dealer, Players players) {
        if (players.isAllPlayersBurst()) {
            return;
        }
        while (dealer.canDraw()) {
            dealer.draw(Deck.pop());
            outputView.printGetMoreCardsMessageForDealer(dealer.getName());
        }
    }

    private void printCardsOfParticipants(Players players, Dealer dealer) {
        outputView.println();
        outputView.printCards(dealer.getName(), dealer.getCardsName(), dealer.calculateCardsValue());
        for (Player player : players.getPlayers()) {
            outputView.printCards(player.getName(), player.getCardsName(), player.calculateCardsValue());
        }
    }

    private void printResult(Bettings bettings, Players players, Dealer dealer) {
        StateResult stateResult = StateResult.from(players, dealer);
        outputView.printResult(ProfitResult.from(players, stateResult, bettings));
    }

}
