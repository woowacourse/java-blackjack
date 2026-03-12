package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.dto.WinningResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Players players = readPlayers();
        Dealer dealer = new Dealer();

        setInitialTwoCards(players, dealer);
        printInitialSettings(players, dealer);

        getMoreCardsForPlayers(players);
        getMoreCardsForDealer(dealer, players);

        printGameResult(players, dealer);
        printWinningResult(players, dealer);
    }

    private Players readPlayers() {
        List<String> playersName = inputView.readPlayersName();
        return Players.from(playersName);
    }

    private void setInitialTwoCards(Players players, Dealer dealer) {
        Deck.shuffle();
        for (int i = 0; i < 2; i++) {
            players.draw();
            dealer.draw(Deck.pop());
        }
    }

    private void printInitialSettings(Players players, Dealer dealer) {
        outputView.printInitialSettingsDoneMessage(dealer.getName(), players.getPlayersName());
        outputView.printCardResults(dealer.getName(), List.of(dealer.getFirstCardName()));
        for (Player player : players.getPlayers()) {
            outputView.printCardResults(player.getName(), player.getCardsName());
        }
        outputView.println();
    }

    private void getMoreCardsForPlayers(Players players) {
        for (Player player : players.getPlayers()) {
            getMoreCardsForPlayer(player);
        }
    }

    private void getMoreCardsForPlayer(Player player) {
        boolean isDraw = false;
        while (player.canDraw() && readPlayerWantMoreCard(player)) {
            player.draw(Deck.pop());
            outputView.printCardResults(player.getName(), player.getCardsName());
            isDraw = true;
        }
        if (!isDraw) {
            outputView.printCardResults(player.getName(), player.getCardsName());
        }
    }

    private boolean readPlayerWantMoreCard(Player player) {
        return "y".equals(inputView.readMoreCard(player.getName()));
    }

    private void getMoreCardsForDealer(Dealer dealer, Players players) {
        if (players.isAllPlayersBurst()) {
            return;
        }
        while (dealer.calculateCardsValue() < 17) {
            dealer.draw(Deck.pop());
            outputView.printGetMoreCardsMessageForDealer(dealer.getName());
        }
    }

    private void printGameResult(Players players, Dealer dealer) {
        outputView.println();
        outputView.printCardResults(dealer.getName(), dealer.getCardsName(), dealer.calculateCardsValue());
        for (Player player : players.getPlayers()) {
            outputView.printCardResults(player.getName(), player.getCardsName(), player.calculateCardsValue());
        }
    }

    private void printWinningResult(Players players, Dealer dealer) {
        outputView.printWinningResult(WinningResult.from(players, dealer));
    }

}
