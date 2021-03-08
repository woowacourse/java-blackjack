package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.List;

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

    public void setUpTwoCards() {
        for (int i = 0; i < SET_UP_CARD_COUNT; i++) {
            players.addCardToPlayer();
            dealer.addCard();
        }
    }

    public void giveCardToPlayer(Player player) {
        player.addCard();
    }

    public void giveCardToDealer() {
        dealer.addCard();
    }

    public int playDealerTurn() {
        int cnt = 0;
        while (!dealer.isStay()) {
            giveCardToDealer();
            cnt++;
        }
        return cnt;
    }

    public void fightPlayers() {
        for (Player player : players.getPlayers()) {
            player.fight(dealer);
        }
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public boolean isPlayerDrawable(Player player) {
        return !player.isBurst() && !player.isBlackJack();
    }

    public boolean isDealerDrawable() {
        return !dealer.isStay();
    }
}