package domain;

import java.util.List;

public class Blackjack {

    private final Players players;
    private final Deck deck;

    public Blackjack(final Players players) {
        this.players = players;
        players.getPlayers().add(new Dealer());
        this.deck = new Deck();
        dealCardsToPlayers();
    }

    public Blackjack(final Players players, final Player dealer) {
        this.players = players;
        this.deck = new Deck();
    }

    public void dealCard(final Player player) {
        player.addCard(deck.draw());
    }

    private void dealCardsToPlayers() {
        players.getPlayers().forEach(this::initializePlayer);
    }

    public BlackjackResultDTO finishGame() {
        final BlackjackResult blackjackResult = new BlackjackResult();
        return blackjackResult.finishGame(getParticipants(), players.getDealer());
    }

    private void initializePlayer(final Player player) {
        player.addCard(deck.draw());
        player.addCard(deck.draw());
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Player getDealer() {
        return players.getDealer();
    }

    public List<Player> getParticipants() {
        return players.getParticipants();
    }
}
