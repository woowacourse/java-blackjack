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

    public void drawCardToPlayer(String answer) {
        Player currentPlayer = currentPlayer();
        if (YES.equals(answer)) {
            currentPlayer.hit(deck.popOne());
        }
        if (NO.equals(answer)) {
            currentPlayer.stay();
        }
    }

    public Player currentPlayer() {
        return this.players.currentPlayer();
    }

    public boolean isAbleToDrawCardToDealer() {
        if (getDealer().isRunning() && getDealer().isHit()) {
            getDealer().hit(this.deck.popOne());
            return true;
        }
        return false;
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
