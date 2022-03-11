package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Gamer;
import blackjack.domain.Gamers;
import blackjack.domain.Name;
import blackjack.domain.OutcomeResults;
import blackjack.domain.Player;
import blackjack.domain.RandomDeck;
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

        Gamer dealer = createDealer(deck);
        Gamers players = createPlayers(names, deck);
        ResultView.printInitCard(dealer, players);

        play(deck, dealer, players);
        printResults(dealer, players);
    }

    private Gamer createDealer(Deck deck) {
        Gamer dealer = new Dealer();
        dealer.hit(deck.pick());
        dealer.hit(deck.pick());
        return dealer;
    }

    private Gamers createPlayers(List<String> names, Deck deck) {
        Gamers gamers = new Gamers(names.stream()
                .map(Name::new)
                .map(Player::new)
                .collect(Collectors.toList()));
        gamers.dealCards(deck);
        return gamers;
    }

    private void play(Deck deck, Gamer dealer, Gamers players) {
        takeTurns(players, deck);
        takeDealerCards(dealer, deck);
    }

    private void takeTurns(Gamers gamers, Deck deck) {
        gamers.get()
                .forEach(gamer -> takePlayerCards(gamer, deck));
    }

    private void takePlayerCards(Gamer gamer, Deck deck) {
        if (canHit(gamer)) {
            gamer.hit(deck.pick());
            ResultView.printGamerCard(gamer);
            takePlayerCards(gamer, deck);
        }
    }

    private boolean canHit(Gamer gamer) {
        String hitOrStayAnswer = InputView.requestHitOrStay(gamer.getName());
        return gamer.isValidRange()
                && hitOrStayAnswer.equals(HIT_ANSWER);
    }

    private void takeDealerCards(Gamer gamer, Deck deck) {
        while (gamer.isValidRange()) {
            ResultView.printDealerHitMessage();
            gamer.hit(deck.pick());
        }
    }

    private void printResults(Gamer dealer, Gamers players) {
        ResultView.printCardsResults(dealer, players);
        ResultView.printOutcomeResults(calculateOutcomeResults(dealer, players));
    }

    private Results calculateOutcomeResults(Gamer dealer, Gamers players) {
        OutcomeResults dealerResult = new OutcomeResults();
        Map<Gamer, OutcomeResults> results = new LinkedHashMap<>();

        for (Gamer player : players.get()) {
            results.put(player, new OutcomeResults());
            results.get(player).increase(Outcome.compare(player, dealer));
            dealerResult.increase(Outcome.compare(dealer, player));
        }

        return new Results(dealerResult, results);
    }
}
