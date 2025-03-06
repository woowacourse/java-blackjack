package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game {
    public static final int INITIAL_HANDS = 2;

    private final Dealer dealer;
    private final Players players;

    public Game(List<PlayerName> playerNames) {
        this.dealer = new Dealer(new Deck(Card.initializeCards()));
        this.players = new Players(playerNames);
    }

    public void giveCardToPlayer(PlayerName playerName, int count) {
        players.giveCard(playerName, drawCards(count));
    }

    public void giveCardToDealer(int count) {
        dealer.addCard(drawCards(count));
    }

    public List<Card> getPlayerCards(PlayerName playerName) {
        return players.getPlayerCard(playerName);
    }

    public void distributeStartingHands() {
        giveCardToDealer(INITIAL_HANDS);
        List<PlayerName> playerNames = players.getUsernames();
        playerNames.forEach(playerName -> giveCardToPlayer(playerName, INITIAL_HANDS));
    }

    public Map<PlayerName, Gamer> getPlayersInfo() {
        return players.getPlayersInfo();
    }

    public Card getDealerOneCard() {
        return dealer.showAnyOneCard();
    }

    public boolean isPlayerDrawable(PlayerName playerName) {
        return players.isDrawable(playerName);
    }

    public boolean isDealerDrawable() {
        return dealer.isDrawable();
    }

    public Gamer getDealer() {
        return dealer.clone();
    }

    public GameStatistics getGameStatistics() {
        return players.calculateGameStatistics(dealer);
    }

    private List<Card> drawCards(int count) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Card newCard = dealer.drawCard();
            cards.add(newCard);
        }
        return cards;
    }
}
