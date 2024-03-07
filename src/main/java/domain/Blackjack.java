package domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Blackjack {

    private final Players players;
    private final Player dealer;
    private final Deck deck;

    // TODO : 필드 수 줄이기
    public Blackjack(final Players players) {
        this.players = players;
        this.dealer = new Player("딜러");
        this.deck = new Deck();
        dealCardsToPlayers();
        dealCardsToDealer();
    }

    public Blackjack(Players players, Player dealer) {
        this.players = players;
        this.dealer = dealer;
        this.deck = new Deck();
    }

    public void dealCard(final Player player) {
        player.addCard(deck.draw());
    }

    private void dealCardsToPlayers() {
        players.getPlayers().forEach(this::initializePlayer);
    }

    private void dealCardsToDealer() {
        dealer.addCard(deck.draw());
        dealer.addCard(deck.draw());
    }

    // "에포케"

    // player > 21, dealer > 21 <- 패배
    // player = 21, dealer = 21 <- 승
    // player <= 21 dealer <= 21
    // player > dealer  -> player sungri
    // player
    // TODO : 로직 개선
    public BlackjackResultDTO finishGame() {
        BlackjackResult blackjackResult = new BlackjackResult();
        return blackjackResult.finishGame(players, dealer);
    }

    private void initializePlayer(final Player player) {
        player.addCard(deck.draw());
        player.addCard(deck.draw());
    }

    public Players getPlayers() {
        return players;
    }

    public Player getDealer() {
        return dealer;
    }
}
