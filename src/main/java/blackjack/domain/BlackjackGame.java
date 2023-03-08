package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.player.User;
import blackjack.domain.result.Result;
import blackjack.domain.result.UserResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public UserResult getResults() {
        HashMap<User, List<Result>> userResults = initializeResults();
        calculateResults(userResults);
        return new UserResult(userResults);
    }

    private void calculateResults(HashMap<User, List<Result>> userResults) {
        int dealerScore = dealer.getTotalScore();
        for (Player player : players.getPlayers()) {
            Result playerResult = Result.calculateResult(player.getTotalScore(), dealerScore);
            Result dealerResult = playerResult.ofOppositeResult();
            userResults.get(player).add(playerResult);
            userResults.get(dealer).add(dealerResult);
        }
    }

    private HashMap<User, List<Result>> initializeResults() {
        HashMap<User, List<Result>> userResults = new HashMap<>();
        userResults.put(dealer, new ArrayList<>());
        for (Player player : players.getPlayers()) {
            userResults.put(player, new ArrayList<>());
        }
        return userResults;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
