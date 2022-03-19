package blackjack.controller;

import blackjack.PlayerProfitResults;
import blackjack.domain.HitOrStayAnswer;
import blackjack.domain.Outcome;
import blackjack.domain.bet.BetMoney;
import blackjack.domain.bet.Profit;
import blackjack.domain.card.Deck;
import blackjack.domain.card.RandomDeck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Name;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
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
            List<Player> participants = toPlayers(InputView.requestNames(), deck);
            return new Players(participants, deck);
        } catch (IllegalArgumentException e) {
            ResultView.printErrorNames(e.getMessage());
            return createPlayers(deck);
        }
    }

    private List<Player> toPlayers(List<String> names, Deck deck) {
        return names.stream()
                .map(Name::new)
                .map(name -> new Participant(name, deck, getBetMoney(name)))
                .collect(Collectors.toList());
    }

    private BetMoney getBetMoney(Name name) {
        int input = InputView.requestBetMoney(name);
        return new BetMoney(input);
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
        if (player.canHit()) {
            player.stay();
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
        ResultView.printProfitResults(calculateProfitResults(players));
    }

    private PlayerProfitResults calculateProfitResults(Players players) {
        Map<Player, Profit> results = new LinkedHashMap<>();
        Player dealer = players.getDealer();
        results.put(dealer, new Profit(0));
        int dealerProfit = 0;

        for (Player player : players.getParticipants()) {
            Outcome playerOutcome = Outcome.matchAboutPlayer((Dealer) dealer, player).not();
            Profit profit = ((Participant) player).getProfit(playerOutcome);
            dealerProfit -= profit.get();
            results.put(player, profit);
        }
        results.put(dealer, new Profit(dealerProfit));
        return new PlayerProfitResults(results);
    }
}
