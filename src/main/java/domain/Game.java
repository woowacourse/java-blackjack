package domain;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final int INITIAL_HANDS = 2;

    private final Dealer dealer;
    private final Players players;

    private Game(List<PlayerName> playerNames, List<BettingMoney> bettingMonies) {
        this.dealer = new Dealer(new Deck(Card.initializeCards()));
        this.players = new Players(playerNames, bettingMonies);
    }

    public static Game initialize(List<PlayerName> playerNames, List<BettingMoney> bettingMonies) {
        Game game = new Game(playerNames, bettingMonies);
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
        dealer.receiveCards(drawCards(count));
    }

    public void giveCardToPlayer(PlayerName playerName, int count) {
        players.giveCardsToPlayer(playerName, drawCards(count));
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

    public Card getDealerOneCard() {
        return dealer.showAnyOneCard();
    }

    public boolean isDealerDrawable() {
        return dealer.isDrawable();
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return Dealer.copyOf(dealer);
    }

    public int calculateDealerProfit() {
        return -players.getPlayers().stream()
                .map(player -> player.calculateProfit(dealer))
                .mapToInt(i -> i)
                .sum();
    }
}
