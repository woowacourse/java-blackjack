package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.player.User;
import blackjack.domain.result.Result;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class BlackjackGame {

    public static final int NUMBER_OF_INITIAL_CARDS = 2;

    private final Players players;
    private final Dealer dealer;

    public BlackjackGame(Players players) {
        this.players = players;
        this.dealer = new Dealer();
    }

    public void giveInitialCardsToUsers() {
        for (Player player : players.getPlayers()) {
            giveInitialCards(player);
        }
        giveInitialCards(dealer);
    }

    private void giveInitialCards(User user) {
        for (int cardIndex = 0; cardIndex < NUMBER_OF_INITIAL_CARDS; cardIndex++) {
            user.updateCardScore();
        }
    }

    public HashMap<User, String> getResults() {
        HashMap<User, String> results = new HashMap<>();
        HashMap<Result, Integer> dealerResults = initializedResults();
        calculateResults(results, dealerResults);
        String dealerResult = dealerResults.keySet().stream()
                        .map(result -> result.getResult() + dealerResults.get(result))
                        .collect(Collectors.joining(" "));
        results.put(dealer, dealerResult);
        return results;
    }

    private void calculateResults(HashMap<User, String> results, HashMap<Result, Integer> dealerResults) {
        int dealerScore = dealer.getTotalScore();
        for (Player player : players.getPlayers()) {
            Result playerResult = Result.calculateResult(player.getTotalScore(), dealerScore);
            Result dealerResult = playerResult.ofOppositeResult();
            results.put(player, playerResult.getResult());
            dealerResults.put(dealerResult, dealerResults.get(dealerResult) + 1);
        }
    }

    private HashMap<Result, Integer> initializedResults() {
        HashMap<Result, Integer> dealerResults = new HashMap<>();
        Arrays.stream(Result.values())
                .forEach(result -> dealerResults.put(result, 0));
        return dealerResults;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
