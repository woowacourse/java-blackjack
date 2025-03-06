package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game {
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

    private List<Card> drawCards(int count) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Card newCard = dealer.drawCard();
            cards.add(newCard);
        }

        return cards;
    }

    public List<Card> getDealerCards() {
        return dealer.getCards();
    }

    public List<Card> getPlayerCards(String username) {
        return players.getPlayerCard(username);
    }

    public void distributeStartingHands() {
        giveCardToDealer(2);
        List<String> usernames = players.getUsernames();
        usernames.forEach(username -> giveCardToPlayer(username, 2));
    }

    public Map<String, Gamer> getPlayersInfo() {
        return players.getPlayersInfo();
    }

    public Card getDealerOneCard() {
        return dealer.showAnyOneCard();
    }

    public boolean canPlayerGetMoreCard(String username) {
        return players.canGetMoreCard(username);
    }

    public boolean canDealerGetMoreCard() {
        return dealer.canGetMoreCard();
    }

    public Gamer getDealer() {
        return dealer.clone();
    }

    public GameStatistics getGameStatistics() {
        return players.calculateGameStatistics(dealer);
    }
}
