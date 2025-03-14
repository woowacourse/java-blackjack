package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game {
    public static final int INITIAL_HANDS = 2;

    private final Dealer dealer;
    private final Players players;

    private Game(List<PlayerName> playerNames) {
        this.dealer = new Dealer(new Deck(Card.initializeCards()));
        this.players = new Players(playerNames);
    }

    public static Game initialize(List<PlayerName> playerNames) {
        Game game = new Game(playerNames);
        game.distributeStartingHands(playerNames);
        return game;
    }

    public Cards getPlayerCards(PlayerName playerName) {
        return players.getPlayerCard(playerName);
    }

    public void distributeStartingHands(List<PlayerName> playerNames) {
        giveCardToDealer(INITIAL_HANDS);
        playerNames.forEach(playerName -> giveCardToPlayer(playerName, INITIAL_HANDS));
    }

    public void giveCardToDealer(int count) {
        dealer.addCard(drawCards(count));
    }

    public void giveCardToPlayer(PlayerName playerName, int count) {
        players.giveCard(playerName, drawCards(count));
    }

    public boolean isPlayerDrawable(PlayerName playerName) {
        return players.isDrawable(playerName);
    }

    private Cards drawCards(int count) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Card newCard = dealer.drawCard();
            cards.add(newCard);
        }
        return new Cards(cards);
    }

    public Map<PlayerName, Player> getPlayersInfo() {
        return players.getPlayersInfo();
    }

    public Card getDealerOneCard() {
        return dealer.showAnyOneCard();
    }

    public boolean isDealerDrawable() {
        return dealer.isDrawable();
    }

    public Gamer getDealer() {
        return Dealer.copyOf(dealer);
    }

    public Map<PlayerName, GameResult> getGameResults() {
        return players.calculateGameStatistics(dealer);
    }
}
