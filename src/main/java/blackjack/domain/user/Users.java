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

    public void giveInitialCardsTo() {
        for (Player player : players.getPlayers()) {
            giveInitialCardsTo(player);
        }
        giveInitialCardsTo(dealer);
    }

    public LinkedHashMap<User, Double> calculateRewards(UserResults userResults) {
        LinkedHashMap<User, Double> playerResults = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            player.updateReceivingAmount(userResults.getResultOf(player));
            player.updateScoreIfBlackjack(dealer.isBlackjack());
            playerResults.put(player, player.getRewards());
        }
        return playerResults;
    }

    public double calculateDealerRewards() {
        double playersTotalBetAmount = players.getTotalBetAmount();
        double playerRewards = players.getPlayers().stream()
                .map(Player::getRewards)
                .reduce(Double::sum)
                .orElseThrow(() -> new IllegalArgumentException("현재 플레이어가 없습니다."));
        return playersTotalBetAmount - playerRewards;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }

    private void giveInitialCardsTo(User user) {
        Deck deck = Deck.getInstance();
        for (int cardIndex = 0; cardIndex < NUMBER_OF_INITIAL_CARDS; cardIndex++) {
            user.updateCardScore(deck.drawCard());
        }
    }
}
