package domain;

import java.util.List;

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

    public void addOneCard(Player player) {
        player.drawOneCard(deck);
    }

    public boolean addCardToDealerIfLowScore() {
        return getDealer().drawOneCardIfLowScore(deck);
    }

    public String getDealerName() {
        return getDealer().getName();
    }

    public Dealer getDealer() {
        return players.getDealer();
    }

    public List<Player> getParticipants() {
        return players.getParticipants();
    }

    public Players getPlayers() {
        return players;
    }
}
