package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.result.Profit;
import blackjack.domain.user.*;

import java.util.List;

public class BlackjackGame {
    private static final String YES = "y";
    private static final String NO = "n";

    private final Deck deck;
    private final Players players;
    private final Users users;
    private final Profit profit;

    public BlackjackGame(Players players) {
        this.deck = new Deck();
        this.players = players;
        this.users = new Users(new Dealer(), this.players);
        this.profit = new Profit();
    }

    public void distributeToUsers() {
        getDealer().drawInitialCards(this.deck.popTwo());
        this.players.drawInitialCardsToPlayers(this.deck);
    }

    public boolean isRunningForPlayers() {
        return this.players.remainAnyPlayer();
    }

    public Player currentPlayer() {
        return this.players.currentPlayer();
    }

    public void drawCardToPlayer(String answer) {
        Player player = this.players.currentPlayer();
        if (YES.equals(answer)) {
            player.hit(deck.popOne());
        }
        if (NO.equals(answer)) {
            player.stay();
        }
    }

    public int drawCardToDealer() {
        int dealerHitCount = 0;
        while (getDealer().isHit()) {
            getDealer().hit(this.deck.popOne());
            dealerHitCount++;
        }
        return dealerHitCount;
    }

    public void calculateProfit() {
        this.profit.addEachPlayerProfit(getDealer(), getPlayers());
        this.profit.addDealerProfit();
    }

    public Dealer getDealer() {
        return this.users.getDealer();
    }

    public List<Player> getPlayers() {
        return this.players.getPlayers();
    }

    public Users getUsers() {
        return this.users;
    }

    public List<Money> getProfit() {
        return this.profit.getProfit();
    }
}
