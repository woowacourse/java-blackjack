package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
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

        Players players = createPlayers(names, deck);
        ResultView.printInitCard(players);

        play(players, deck);
        printResults(players);
    }

    private Players createPlayers(List<String> names, Deck deck) {
        List<Player> participants = names.stream()
                .map(Name::new)
                .map(Participant::new)
                .collect(Collectors.toList());

        Players players = new Players(participants);
        players.dealCards(deck);
        return players;
    }

    private void play(Players players, Deck deck) {
        takeParticipantTurns(players.getParticipants(), deck);
        takeDealerCards(players.getDealer(), deck);
    }

    private void takeParticipantTurns(List<Player> participants, Deck deck) {
        participants.forEach(participant -> takeParticipantCards(participant, deck));
    }

    private void takeParticipantCards(Player player, Deck deck) {
        if (canHit(player)) {
            player.hit(deck.pick());
            ResultView.printPlayerCard(player);
            takeParticipantCards(player, deck);
        }
    }

    private boolean canHit(Player player) {
        if (!player.isValidRange()) {
            return false;
        }
        return InputView.requestHitOrStay(player.getName()).equals(HIT_ANSWER);
    }

    private void takeDealerCards(Player dealer, Deck deck) {
        while (dealer.isValidRange()) {
            ResultView.printDealerHitMessage();
            dealer.hit(deck.pick());
        }
    }

    private void printResults(Players players) {
        ResultView.printCardsResults(players);
        ResultView.printOutcomeResults(calculateOutcomeResults(players));
    }

    private Results calculateOutcomeResults(Players players) {
        Map<Player, OutcomeResults> results = new LinkedHashMap<>();
        Player dealer = players.getDealer();
        results.put(dealer, new OutcomeResults());

        for (Player player : players.getParticipants()) {
            Outcome outcome = Outcome.match((Dealer) dealer, player);
            results.get(dealer).increase(outcome);
            results.put(player, new OutcomeResults());
            results.get(player).increase(outcome.not());
        }
        return new Results(results);
    }
}
