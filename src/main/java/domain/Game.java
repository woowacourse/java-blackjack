package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game {
    public static final int INITIAL_HANDS = 2;

    private final Dealer dealer;
    private final Players players;

    public Game(List<String> usernames) {
        this.dealer = new Dealer(new Deck(Card.initializeCards()));
        this.players = new Players(usernames);
    }

    public void giveCardToPlayer(String username, int count) {
        players.giveCard(username, drawCards(count));
    }

    public void giveCardToDealer(int count) {
        dealer.addCard(drawCards(count));
    }

    public List<Card> getPlayerCards(String username) {
        return players.getPlayerCard(username);
    }

    public void distributeStartingHands() {
        giveCardToDealer(INITIAL_HANDS);
        List<String> usernames = players.getUsernames();
        usernames.forEach(username -> giveCardToPlayer(username, INITIAL_HANDS));
    }

    public Map<String, Gamer> getPlayersInfo() {
        return players.getPlayersInfo();
    }

    public Card getDealerOneCard() {
        return dealer.showAnyOneCard();
    }

    public boolean isPlayerDrawable(String username) {
        return players.isDrawable(username);
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
