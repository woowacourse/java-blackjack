package blackjack.controller;

import blackjack.domain.betting.BettingAmounts;
import blackjack.domain.deck.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.dto.WinningResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

        BettingAmounts bettingAmounts = readBettingAmounts(players);

        setInitialTwoCards(players, dealer);
        printInitialSettings(players, dealer);

        getMoreCardsForPlayers(players);
        getMoreCardsForDealer(dealer, players);

        printGameResult(players, dealer);
        printWinningResult(bettingAmounts, players, dealer);
    }

    private Players readPlayers() {
        List<String> playersName = inputView.readPlayersName();
        return Players.from(playersName);
    }

    private BettingAmounts readBettingAmounts(Players players) {
        BettingAmounts bettingAmounts = new BettingAmounts();
        for (Player player : players.getPlayers()) {
            String playerName = player.getName();
            bettingAmounts.put(playerName, inputView.readBettingAmount(playerName));
        }
        return bettingAmounts;
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
        while (dealer.canDraw()) {
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

    private void printWinningResult(BettingAmounts bettingAmounts, Players players, Dealer dealer) {
        Map<String, Integer> winningResult = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            if (player.winsAgainst(dealer)) {
                winningResult.put(player.getName(), bettingAmounts.findByName(player.getName()));
            }
        }
        outputView.printWinningResult(WinningResult.from(winningResult));
    }

}
