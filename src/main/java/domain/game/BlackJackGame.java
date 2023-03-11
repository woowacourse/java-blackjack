package domain.game;

import domain.card.Card;
import domain.card.Deck;
import domain.player.Player;
import domain.player.Players;
import domain.strategy.ShuffleStrategy;

import java.util.List;

public class BlackJackGame {
    private final Deck deck;
    private final Players players;

    public BlackJackGame(String participantNames, ShuffleStrategy shuffleStrategy) {
        this.deck = Deck.from(shuffleStrategy);
        this.players = new Players(participantNames);
    }

    public void giveTwoCardToPlayers() {
        players.giveTwoCardToPlayers(deck);
    }

    private Card draw() {
        return deck.draw();
    }

    public void giveCard(Player player) {
        player.draw(draw());
    }
    
    public boolean isDealerFinished() {
        return getDealer().isFinished();
    }
    
    public void dealerDrawStop() {
        getDealer().drawStop();
    }
    
    public Player getDealer() {
        return players.getDealer();
    }
    
    public List<Player> getParticipants() {
        return players.getParticipants();
    }
    
    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
