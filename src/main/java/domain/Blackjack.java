package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Blackjack {

    private final Players players;
    private final Player dealer;
    private final Deck deck;

    // TODO : 필드 수 줄이기
    public Blackjack(final Players players) {
        this.players = players;
        this.dealer = new Dealer(new Name("딜러"));
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

    public BlackjackResultDTO finishGame() {
        BlackjackResult blackjackResult = new BlackjackResult();
        return blackjackResult.finishGame(players, dealer);
    }

    private void initializePlayer(final Player player) {
        player.addCard(deck.draw());
        player.addCard(deck.draw());
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Player getDealer() {
        return dealer;
    }
}
