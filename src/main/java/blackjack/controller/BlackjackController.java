package blackjack.controller;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.card.deck.Deck;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.gamer.PlayerName;
import blackjack.domain.result.OutcomeResults;
import blackjack.domain.gamer.Player;
import blackjack.domain.card.deck.RandomDeck;
import blackjack.domain.result.Outcome;
import blackjack.domain.result.Results;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    private static final String STAY_ANSWER = "n";

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
                .map(PlayerName::new)
                .map(Player::new)
                .collect(Collectors.toList()));
        gamers.initCards(deck);
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
        if (!gamer.isValidRange()) {
            return;
        }
        String answer = InputView.requestHitOrStay(gamer.getName());
        if (answer.equals(STAY_ANSWER)) {
            return;
        }
        gamer.hit(deck.pick());
        ResultView.printGamerCard(gamer);
        takePlayerCards(gamer, deck);
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
