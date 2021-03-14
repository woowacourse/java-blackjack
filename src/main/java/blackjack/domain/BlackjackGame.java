package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.result.Profit;
import blackjack.domain.user.*;

import java.util.List;

public class BlackjackGame {
    private final Deck deck;
    private final Users users;
    private final Profit profit;

    public BlackjackGame(Players players) {
        this.deck = new Deck();
        this.users = new Users(new Dealer(), players);
        this.profit = new Profit();
    }

    public void distributeToUsers() {
        getDealer().distribute(this.deck.popTwo());
        this.users.distributeToPlayers(this.deck);
    }

    public void drawCardToPlayer(Player player) {
        player.draw(this.deck);
    }

    public boolean isPlayerHit(Player player) {
        return player.isHit();
    }

    public boolean drawCardToDealer() {
        if (isDealerHit()) {
            getDealer().draw(this.deck);
            return true;
        }
        return false;
    }

    public void calculateProfit() {
        this.profit.addEachPlayerProfit(getDealer(), getPlayers());
        this.profit.addDealerProfit();
    }

    private boolean isDealerHit() {
        return getDealer().isHit();
    }

    public Users getUsers() {
        return this.users;
    }

    public Dealer getDealer() {
        return this.users.getDealer();
    }

    public List<Player> getPlayers() {
        return this.users.getPlayers();
    }

    public List<Money> getProfit() {
        return this.profit.getProfit();
    }
}
