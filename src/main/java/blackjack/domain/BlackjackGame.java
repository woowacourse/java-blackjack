package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Money;
import blackjack.domain.user.Player;
import blackjack.domain.user.Users;

import java.util.List;

public class BlackjackGame {
    private final Deck deck;
    private final Users users;

    public BlackjackGame(List<String> names, List<Double> moneyGroup) {
        this.deck = new Deck();
        this.users = new Users(new Dealer(), names, moneyGroup);
    }

    public void distributeToUsers() {
        getDealer().distribute(this.deck.popTwo());
        this.users.distributeToPlayer(this.deck);
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
        return this.users.getProfit();
    }
}
