package domain;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static final int INITIAL_HANDS = 2;
    private static final int DEFAULT_CARDS_PER_TURN = 1;

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

    public void giveDefaultCardsToDealer() {
        giveCardToDealer(DEFAULT_CARDS_PER_TURN);
    }

    public void giveDefaultCardsToPlayer(PlayerName playerName) {
        giveCardToPlayer(playerName, DEFAULT_CARDS_PER_TURN);
    }

    private void giveCardToDealer(int count) {
        dealer.receiveCards(drawCards(count));
    }

    private void giveCardToPlayer(PlayerName playerName, int count) {
        players.giveCardsToPlayer(playerName, drawCards(count));
    }

    private Cards drawCards(int count) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Card newCard = dealer.drawCard();
            cards.add(newCard);
        }
        return new Cards(cards);
    }

    public boolean isPlayerDrawable(PlayerName playerName) {
        return players.isDrawable(playerName);
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
