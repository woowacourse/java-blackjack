package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.player.User;
import blackjack.domain.result.Result;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BlackjackGame {

    public static final int NUMBER_OF_INITIALIZED_CARDS = 2;

    private final Players players;
    private final Dealer dealer;

    public BlackjackGame(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void giveInitializedCards() {
        for (Player player : players.getPlayers()) {
            giveFirstCard(player);
        }
        giveFirstCard(dealer);
    }

    private void giveFirstCard(User user) {
        Deck deck = Deck.getInstance();
        for (int cardIndex = 0; cardIndex < NUMBER_OF_INITIALIZED_CARDS; cardIndex++) {
            user.updateCardScore(deck.giveFirstCard());
        }
    }

    public List<HashMap> getResults() {
        HashMap<Player, Result> playerResults = new HashMap<>();
        HashMap<Result, Integer> dealerResults = initializedResults();
        calculateResults(playerResults, dealerResults);
        return List.of(playerResults, dealerResults);
    }

    private void calculateResults(HashMap<Player, Result> playerResults, HashMap<Result, Integer> dealerResults) {
        int dealerScore = dealer.getTotalScore();
        for (Player player : players.getPlayers()) {
            Result playerResult = Result.calculateResult(player.getTotalScore(), dealerScore);
            Result dealerResult = playerResult.ofOppositeResult();
            playerResults.put(player, playerResult);
            dealerResults.put(dealerResult, dealerResults.get(dealerResult) + 1);
        }
    }

    private HashMap<Result, Integer> initializedResults() {
        HashMap<Result, Integer> dealerResults = new HashMap<>();
        Arrays.stream(Result.values())
                .forEach(result -> dealerResults.put(result, 0));
        return dealerResults;
    }
}
