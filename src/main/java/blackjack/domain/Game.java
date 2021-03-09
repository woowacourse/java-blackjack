package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.List;
import java.util.Map;

public class Game {
    public static final int BLACKJACK_NUMBER = 21;
    private static final int SET_UP_CARD_COUNT = 2;

    private final Dealer dealer;
    private final Players players;

    private Game(Players players) {
        this.dealer = new Dealer();
        this.players = players;
        setUpTwoCards();
    }

    public static Game of(Players players) {
        return new Game(players);
    }

    private void setUpTwoCards() {
        for (int i = 0; i < SET_UP_CARD_COUNT; i++) {
            players.addCardToPlayer();
            dealer.addCard(Deck.draw());
        }
    }

    public void giveCardToPlayer(Player player) {
        player.addCard(Deck.draw());
    }

    public void giveCardToDealer() {
        dealer.addCard(Deck.draw());
    }

    public boolean isPlayerDrawable(Player player) {
        return !player.isBurst() && !player.isBlackJack();
    }

    public boolean isDealerDrawable() {
        return !dealer.isStay();
    }

    public String getDealerGameResult(Map<String, String> playersGameResult) {
        return dealer.getGameResult(playersGameResult);
    }

    public Map<String, String> getPlayersGameResult() {
        return players.getGameResults(dealer);
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }
}