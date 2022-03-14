package blackjack.controller;

import blackjack.domain.user.Dealer;
import blackjack.domain.card.deck.Deck;
import blackjack.domain.user.User;
import blackjack.domain.user.Players;
import blackjack.domain.user.UserName;
import blackjack.domain.result.OutcomeResults;
import blackjack.domain.user.Player;
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

    private static final String HIT = "y";

    public void run() {
        List<String> names = InputView.inputPlayerNames();
        Deck deck = new RandomDeck();

        User dealer = createDealer();
        Players players = createPlayers(names);
        takeInitHand(dealer, players, deck);

        play(deck, dealer, players);
        printResults(dealer, players);
    }

    private User createDealer() {
        return new Dealer();
    }

    private Players createPlayers(List<String> names) {
        return new Players(names.stream()
                .map(UserName::new)
                .map(Player::new)
                .collect(Collectors.toList()));
    }

    private void takeInitHand(User dealer, Players players, Deck deck) {
        dealer.takeInitHand(deck);
        players.takeInitHand(deck);
        ResultView.printInitHand(dealer, players);
    }

    private void play(Deck deck, User dealer, Players players) {
        takeTurns(players, deck);
        takeDealerCards(dealer, deck);
    }

    private void takeTurns(Players players, Deck deck) {
        players.get()
                .forEach(player -> takePlayerCards(player, deck));
    }

    private void takePlayerCards(User user, Deck deck) {
        if (!user.isValidRange()) {
            return;
        }

        String answer = InputView.inputHitOrStay(user.getName());
        if (answer.equals(HIT)) {
            user.hit(deck.pick());
            ResultView.printHand(user);
            takePlayerCards(user, deck);
        }
    }

    private void takeDealerCards(User user, Deck deck) {
        while (user.isValidRange()) {
            ResultView.printDealerHitMessage();
            user.hit(deck.pick());
        }
    }

    private void printResults(User dealer, Players players) {
        ResultView.printCardsResults(dealer, players);
        ResultView.printOutcomeResults(calculateOutcomeResults(dealer, players));
    }

    private Results calculateOutcomeResults(User dealer, Players players) {
        OutcomeResults dealerResult = new OutcomeResults();
        Map<User, OutcomeResults> results = new LinkedHashMap<>();
        for (User player : players.get()) {
            results.put(player, new OutcomeResults());
            results.get(player).increase(player.determineWinner(dealer));
            dealerResult.increase(dealer.determineWinner(player));
        }
        return new Results(dealerResult, results);
    }
}
