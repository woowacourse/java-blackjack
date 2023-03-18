package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.player.*;
import blackjack.domain.result.GameResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameController {
    private static final int INIT_FIRST_CARD = 2;

    public void run() {
        Dealer dealer = new Dealer();
        Deck deck = Deck.initializeCards();
        List<Name> names = (InputView.repeat(() -> new Names(InputView.inputPeopleNames()))).getNames();
        Players players = initializePlayers(names);

        giveFirstCardsForUsers(players, dealer, deck);

        OutputView.printReadyMessage(names);
        printUserFirstCards(players.getPlayers(), dealer);

        giveCardOrNotForPlayers(players, deck);
        giveCardOrNotForDealer(dealer, deck);

        OutputView.printResults(dealer, players.getPlayers());
        printScore(players, dealer);
    }

    private Players initializePlayers(List<Name> names) {
        List<Player> players = new ArrayList<>();
        for (Name name : names) {
            int betAmount = InputView.repeat(() -> InputView.inputBetAmount(name.getName()));
            players.add(new Player(new Name(name.getName()), new BetAmount(betAmount)));
        }
        return new Players(players);
    }

    private void giveFirstCardsForUsers(Players players, Dealer dealer, Deck deck) {
        for (Player player : players.getPlayers()) {
            giveFirstCards(player, deck);
        }
        giveFirstCards(dealer, deck);
    }

    private void giveFirstCards(User user, Deck deck) {
        for (int i = 0; i < INIT_FIRST_CARD; i++) {
            user.addCard(deck.draw());
        }
    }

    private void printUserFirstCards(List<Player> players, Dealer dealer) {
        OutputView.printDealerFirstCard(dealer);
        for (Player player : players) {
            OutputView.printPlayerCurrentCards(player);
        }
        System.out.println();
    }

    private void giveCardOrNotForPlayers(Players players, Deck shuffledDeck) {
        for (Player player : players.getPlayers()) {
            giveCardOrNotForPlayer(player, shuffledDeck);
        }
    }

    private void giveCardOrNotForPlayer(Player player, Deck deck) {
        while (player.isUnderLimit() &&
                (InputView.repeat(() -> InputView.askAdditionalCard(player.getUserName())))) {
            player.addCard(deck.draw());
            OutputView.printPlayerCurrentCards(player);
        }
    }

    private void giveCardOrNotForDealer(Dealer dealer, Deck deck) {
        while (dealer.isUnderLimit()) {
            OutputView.printMessageDealerOneMore();
            dealer.addCard(deck.draw());
        }
    }

    private void printScore(Players players, Dealer dealer) {
        GameResult gameResult = new GameResult(players.getPlayers(), dealer);
        Map<String, Integer> playersScore = gameResult.getPlayerGameResults();
        OutputView.printScore(playersScore, gameResult.calculateDealerProfit());
    }
}
