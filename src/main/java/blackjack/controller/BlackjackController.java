package blackjack.controller;

import blackjack.domain.player.Dealer;
import blackjack.domain.card.Deck;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.player.Name;
import blackjack.domain.OutcomeResults;
import blackjack.domain.player.Participant;
import blackjack.domain.card.RandomDeck;
import blackjack.domain.Outcome;
import blackjack.domain.Results;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    private static final String HIT_ANSWER = "y";

    public void run() {
        List<String> names = InputView.requestNames();
        Deck deck = new RandomDeck();

        Player dealer = createDealer(deck);
        Players players = createPlayers(names, deck);
        ResultView.printInitCard(dealer, players);

        play(deck, dealer, players);
        printResults(dealer, players);
    }

    private Player createDealer(Deck deck) {
        Player dealer = new Dealer();
        dealer.hit(deck.pick());
        dealer.hit(deck.pick());
        return dealer;
    }

    private Players createPlayers(List<String> names, Deck deck) {
        Players players = new Players(names.stream()
                .map(Name::new)
                .map(Participant::new)
                .collect(Collectors.toList()));
        players.dealCards(deck);
        return players;
    }

    private void play(Deck deck, Player dealer, Players players) {
        takeTurns(players, deck);
        takeDealerCards(dealer, deck);
    }

    private void takeTurns(Players players, Deck deck) {
        players.get()
                .forEach(gamer -> takePlayerCards(gamer, deck));
    }

    private void takePlayerCards(Player player, Deck deck) {
        if (canHit(player)) {
            player.hit(deck.pick());
            ResultView.printGamerCard(player);
            takePlayerCards(player, deck);
        }
    }

    private boolean canHit(Player player) {
        String hitOrStayAnswer = InputView.requestHitOrStay(player.getName());
        return player.isValidRange()
                && hitOrStayAnswer.equals(HIT_ANSWER);
    }

    private void takeDealerCards(Player player, Deck deck) {
        while (player.isValidRange()) {
            ResultView.printDealerHitMessage();
            player.hit(deck.pick());
        }
    }

    private void printResults(Player dealer, Players players) {
        ResultView.printCardsResults(dealer, players);
        ResultView.printOutcomeResults(calculateOutcomeResults(dealer, players));
    }

    private Results calculateOutcomeResults(Player dealer, Players players) {
        OutcomeResults dealerResult = new OutcomeResults();
        Map<Player, OutcomeResults> results = new LinkedHashMap<>();

        for (Player player : players.get()) {
            results.put(player, new OutcomeResults());
            results.get(player).increase(Outcome.compare(player, dealer));
            dealerResult.increase(Outcome.compare(dealer, player));
        }

        return new Results(dealerResult, results);
    }
}
