package blackjack.domain.user;

import blackjack.domain.card.Deck;
import blackjack.domain.result.UserResults;

import java.util.LinkedHashMap;

public class Users {

    public static final int NUMBER_OF_INITIAL_CARDS = 2;

    private final Players players;
    private final Dealer dealer;

    public Users(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void giveInitialCards() {
        for (Player player : players.getPlayers()) {
            giveInitialCards(player);
        }
        giveInitialCards(dealer);
    }

    private void giveInitialCards(User user) {
        Deck deck = Deck.getInstance();
        for (int cardIndex = 0; cardIndex < NUMBER_OF_INITIAL_CARDS; cardIndex++) {
            user.updateCardScore(deck.drawCard());
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }

    public LinkedHashMap<User, Double> calculateReceivingAmount(UserResults userResults) {
        LinkedHashMap<User, Double> playerResults = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            player.updateReceivingAmount(userResults.getResultOf(player));
            player.checkBlackjack(dealer.isBlackjack());
            playerResults.put(player, player.getReceivingAmount());
        }
        return playerResults;
    }

    public double calculateDealerReceivingAmount() {
        double dealerReceivingAmount = 0;
        for (Player player : players.getPlayers()) {
            dealerReceivingAmount -= (player.getReceivingAmount());
        }
        return dealerReceivingAmount;
    }
}
