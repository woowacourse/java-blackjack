package domain.game;

import domain.card.Card;
import domain.card.Deck;
import domain.participants.BettingAmount;
import domain.participants.Dealer;
import domain.participants.Gamer;
import domain.participants.PlayerName;
import domain.participants.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game {
    public static final int INITIAL_HANDS = 2;

    private final Dealer dealer;
    private final Players players;

    private Game(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Game(Map<PlayerName, BettingAmount> playerInfo) {
        this(new Dealer(new Deck(Card.initializeCards())), new Players(playerInfo));
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
        return dealer.newInstance();
    }

    public GameStatistics getGameStatistics() {
        return players.calculateGameStatistics(dealer);
    }

    public BettingStatistics getBettingStatistics() {
        return players.calculateBettingStatistics(dealer);
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
