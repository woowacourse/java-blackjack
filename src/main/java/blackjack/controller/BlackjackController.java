package blackjack.controller;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Participant;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.factory.DealerFactory;
import blackjack.factory.DeckFactory;
import blackjack.factory.PlayersFactory;
import blackjack.view.Confirmation;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    private final DeckFactory deckFactory;

    public BlackjackController(DeckFactory deckFactory) {
        this.deckFactory = deckFactory;
    }

    public void run() {
        List<String> names = InputView.readNames();
        List<Integer> bettingMoneyList = InputView.readBettingMoneyList(names);
        Deck deck = deckFactory.generate();
        Players players = PlayersFactory.generate(names, bettingMoneyList);
        Dealer dealer = DealerFactory.generate();

        executeGameFlow(deck, players, dealer);

        printResult(players, dealer);
    }

    private void executeGameFlow(Deck deck, Players players, Dealer dealer) {
        giveStartingCards(deck, players, dealer);

        giveMoreCardFor(deck, players);
        giveMoreCardFor(deck, dealer);
    }

    private void giveStartingCards(Deck deck, Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            giveStartingCardsFor(deck, player);
        }

        giveStartingCardsFor(deck, dealer);

        OutputView.printStartingCardsStatuses(dealer, players);
    }

    private void giveMoreCardFor(Deck deck, Players players) {
        for (Player player : players.getPlayers()) {
            giveMoreCardFor(deck, player);
        }
    }

    private void giveMoreCardFor(Deck deck, Dealer dealer) {
        while (dealer.canTakeCard()) {
            OutputView.printMoreCard();
            giveCard(deck, dealer);
        }
    }

    private void giveMoreCardFor(Deck deck, Player player) {
        Confirmation confirmation = InputView.askToGetMoreCard(player);
        if (confirmation.equals(Confirmation.N)) {
            OutputView.printCardResult(player);
            return;
        }

        giveCard(deck, player);
        OutputView.printCardResult(player);

        if (player.isBusted()) {
            OutputView.printBustedPlayer(player);
            return;
        }

        if (player.canTakeCard()) {
            giveMoreCardFor(deck, player);
        }
    }

    private void giveStartingCardsFor(Deck deck, Participant participant) {
        List<Card> cards = deck.takeStartCards();

        cards.forEach(participant::takeCard);
    }

    private void giveCard(Deck deck, Participant participant) {
        List<Card> cards = deck.takeOneCard();

        cards.forEach(participant::takeCard);
    }

    private void printResult(Players players, Dealer dealer) {
        players.adjustBalance(dealer);

        int playersTotalRevenue = players.getTotalRevenue();
        Map<Player, Integer> revenueMap = players.getRevenueMap();

        OutputView.printCardResult(players, dealer);
        OutputView.printRevenue(playersTotalRevenue, revenueMap);
    }
}
