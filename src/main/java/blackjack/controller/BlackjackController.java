package blackjack.controller;

import blackjack.domain.HitOrStayAnswer;
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

    public void run() {
        Deck deck = new RandomDeck();

        Players players = createPlayers(deck);
        ResultView.printInitCard(players);

        play(players, deck);
        printResults(players);
    }

    private Players createPlayers(Deck deck) {
        try {
            List<Player> participants = toPlayers(InputView.requestNames());
            Players players = new Players(participants);
            players.dealCards(deck);
            return players;
        } catch (IllegalArgumentException e) {
            ResultView.printErrorNames(e.getMessage());
            return createPlayers(deck);
        }
    }

    private List<Player> toPlayers(List<String> names) {
        return names.stream()
                .map(Name::new)
                .map(Participant::new)
                .collect(Collectors.toList());
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
        if (!player.canHit()) {
            return false;
        }

        String answer;
        do {
            answer = InputView.requestHitOrStay(player.getName());
            printErrorIfNotContainsHitOrStay(answer);
        } while (!HitOrStayAnswer.containsValue(answer));
        return answer.equals(HitOrStayAnswer.HIT_ANSWER.get());
    }

    private void printErrorIfNotContainsHitOrStay(String answer) {
        if (!HitOrStayAnswer.containsValue(answer)) {
            ResultView.printErrorHitOrStay();
        }
    }

    private void takeDealerCards(Player dealer, Deck deck) {
        while (dealer.canHit()) {
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
