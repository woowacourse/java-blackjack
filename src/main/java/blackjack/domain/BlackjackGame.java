package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.result.Rewards;
import blackjack.domain.user.*;
import blackjack.domain.result.UserResults;

import java.util.LinkedHashMap;

public class BlackjackGame {

    private final Users users;
    private final Deck deck;

    public BlackjackGame(Players players) {
        users = new Users(new Dealer(), players);
        deck = Deck.getInstance();
    }

    public void giveInitialCardsToUsers() {
        deck.shuffleCards();
        users.giveInitialCardsTo();
    }

    public void updateCard(User user) {
        user.updateCardScore(deck.drawCard());
    }

    public Players getPlayers() {
        return users.getPlayers();
    }

    public Dealer getDealer() {
        return users.getDealer();
    }

    public Rewards getRewards() {
        UserResults results = getResults();
        LinkedHashMap<User, Double> rewards = calculatePlayersRewards(results);
        rewards.put(getDealer(), calculateDealerRewards());
        return Rewards.of(rewards);
    }

    private LinkedHashMap<User, Double> calculatePlayersRewards(UserResults results) {
        return users.calculateRewards(results);
    }

    private double calculateDealerRewards() {
        return users.calculateDealerRewards();
    }

    private UserResults getResults() {
        return UserResults.of(users);
    }
}
