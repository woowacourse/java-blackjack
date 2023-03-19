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

    public boolean addCardToDealerIfPossible() {
        return this.dealer.drawCardDealer();
    }

    public BlackjackResult calculateAllResults() {
        return this.dealer.calculateAllResults(players.getPlayers());
    }

    public void passPlayer() {
        this.players.nextPlayer();
    }

    public boolean isCurrentPlayerCanAdd() {
        return this.players.currentPlayerCanAdd();
    }

    public String getCurrentPlayerName() {
        Player player = this.players.getCurrnetPlayer();
        return player.getNameValue();
    }

    public void addCardToCurrentPlayer() {
        Player player = this.players.getCurrnetPlayer();
        player.addCard(dealer.drawCard());
    }

    public boolean isContinuable() {
        return this.players.isContinuable();
    }

    public CardHand getCurrentPlayerCardHand() {
        return this.players.currentPlayerCardHand();
    }
}
