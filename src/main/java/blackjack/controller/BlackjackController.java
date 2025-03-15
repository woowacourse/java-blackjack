package blackjack.controller;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Participant;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.factory.DealerFactory;
import blackjack.factory.DeckGenerator;
import blackjack.factory.PlayersFactory;
import blackjack.view.Confirmation;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class BlackjackController {

    private final DeckGenerator deckGenerator;

    public BlackjackController(DeckGenerator deckGenerator) {
        this.deckGenerator = deckGenerator;
    }

    public void run() {
        List<String> names = InputView.readNames();
        List<Integer> bettingMoneyList = InputView.readBettingMoneyList(names);
        Deck deck = deckGenerator.generate();
        Players players = PlayersFactory.generate(names, bettingMoneyList);
        Dealer dealer = DealerFactory.generate();

        giveCards(deck, players, dealer);

        printResult(players, dealer);
    }

    private void giveCards(Deck deck, Players players, Dealer dealer) {
        giveStartingCards(deck, players, dealer);

        giveMoreCardFor(deck, players);
        giveMoreCardFor(deck, dealer);
    }

    private void printResult(Players players, Dealer dealer) {
        players.adjustBalance(dealer);

        int playersTotalRevenue = players.getTotalRevenue();
        Map<Player, Integer> revenueMap = players.getRevenueMap();

        OutputView.printCardResult(players, dealer);
        OutputView.printRevenue(playersTotalRevenue, revenueMap);
    }

    private void giveStartingCards(Deck deck, Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            giveCard(Deck::takeStartCards, deck, player);
        }

        giveCard(Deck::takeStartCards, deck, dealer);

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
            giveCard(Deck::takeOneCard, deck, dealer);
        }
    }

    private void giveCard(Function<Deck, List<Card>> function, Deck deck, Participant participant) {
        List<Card> cards = function.apply(deck);

        cards.forEach(participant::takeCard);
    }

    private void giveMoreCardFor(Deck deck, Player player) {
        while (canReceiveMoreCard(player)) {
            giveCard(Deck::takeOneCard, deck, player);
            OutputView.printCardStatus(player);
        }
    }

    private boolean canReceiveMoreCard(Player player) {
        return notIsBustedFor(player) && player.canTakeCard() && InputView.askToGetMoreCard(player) != Confirmation.N;
    }

    private boolean notIsBustedFor(Player player) {
        boolean busted = player.isBusted();
        if (busted) {
            OutputView.printBustedPlayer(player);
        }

        return !busted;
    }
}
