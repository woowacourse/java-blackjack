package domain;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import java.util.List;

public class Participants {

    private final Dealer dealer;
    private final Players players;

    public Participants(List<Player> players) {
        this.dealer = new Dealer();
        this.players = new Players(players);
    }

    public void initGame() {
        dealer.drawInitCardsDealer();
        drawPlayerInitCards(dealer, players);
    }

    public void drawPlayerInitCards(Dealer dealer, Players players) {
        players.getPlayers().forEach(player -> {
            addPlayerCards(player, dealer.drawInitCards());
        });
    }

    public void addPlayerCards(Player player, List<Card> cards) {
        cards.forEach(player::addCard);
    }


    public Dealer getDealer() {
        return this.dealer;
    }

    public List<Player> getPlayers() {
        return this.players.getPlayers();
    }

    public void addCardToDealerIfPossible() {
        this.dealer.drawCardDealer();
    }

    public void calculateAllResults() {
        this.dealer.calculateAllResults(players.getPlayers());
    }

    public PlayerRevenues getPlayerRevenues() {
        return this.dealer.getPlayerRevenues();
    }
}
