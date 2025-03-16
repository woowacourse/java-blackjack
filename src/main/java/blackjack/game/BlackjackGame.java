package blackjack.game;

import blackjack.card.CardDeck;
import blackjack.user.dealer.Dealer;
import blackjack.user.player.Players;
import blackjack.user.player.PlayerName;
import java.util.List;

public class BlackjackGame {

    private static final int INITIAL_DISTRIBUTE_CARD_NUMBER = 2;
    private static final int EXTRA_DISTRIBUTE_CARD_NUMBER = 1;

    private final Dealer dealer;
    private final Players players;

    private BlackjackGame(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static BlackjackGame createGameWith(final CardDeck cardDeck, final Players players) {
        Dealer dealer = Dealer.createDealer(cardDeck);
        return new BlackjackGame(dealer, players);
    }

    public void initCardsToUsers() {
        dealer.addCards(INITIAL_DISTRIBUTE_CARD_NUMBER);
        players.addPickedCards(dealer, INITIAL_DISTRIBUTE_CARD_NUMBER);
    }

    public void addExtraCardToDealer() {
        dealer.addCards(EXTRA_DISTRIBUTE_CARD_NUMBER);
    }

    public void addExtraCardToPlayer(final PlayerName name) {
        players.addExtraCardToPlayer(dealer, name, EXTRA_DISTRIBUTE_CARD_NUMBER);
    }

    public int calculateProfitForPlayers() {
        int totalProfit = players.calculatePlayersProfit(dealer);
        return totalProfit;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }

    public List<PlayerName> getPlayerNames() {
        return players.getPlayerNames();
    }
}
