package domain;

import domain.card.Card;
import domain.card.Deck;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;
import domain.player.User;
import domain.profit.Profit;
import domain.profit.ProfitStrategy;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class BlackjackManager {

    private final Players players;
    private final Deck deck;

    public BlackjackManager(Players players, Deck deck) {
        this.players = players;
        this.deck = deck;
    }

    public void distributeInitialCards() {
        players.distributeInitialCards(deck);
    }

    public void openInitialCards() {
        players.openInitialCards();
    }

    public void allUsersHitUntilStay(Function<User, Boolean> wantHit,
                                     BiConsumer<User, List<Card>> onHit) {
        for (User user : players.getUsers()) {
            userHitUntilStay(user, wantHit, onHit);
        }
    }

    private void userHitUntilStay(User user,
                                  Function<User, Boolean> wantHit,
                                  BiConsumer<User, List<Card>> onHit) {
        while (!user.isBust() && wantHit.apply(user)) {
            user.drawOneCard(deck);
            onHit.accept(user, user.getCards());
        }
    }

    public void dealerHitUntilStay(Runnable onHit) {
        Dealer dealer = getDealer();
        while (dealer.canHit()) {
            dealer.drawOneCard(deck);
            onHit.run();
        }
    }

    public Map<Player, Integer> computePlayerSum() {
        return players.computePlayerSum();
    }

    public Map<Dealer, Profit> computeDealerProfit() {
        return players.computeDealerProfit();
    }

    public Map<User, Profit> computeUsersProfit(ProfitStrategy profitStrategy) {
        return players.computeUsersProfit(profitStrategy);
    }

    public Map<User, BattleResult> computeUsersBattleResult() {
        return players.computeUsersBattleResult();
    }

    public Dealer getDealer() {
        return players.getDealer();
    }

    public List<User> getUsers() {
        return players.getUsers();
    }
}
