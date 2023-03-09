package domain.game;

import domain.Card.Deck;
import domain.game.GameResult.Result;
import domain.user.Dealer;
import domain.user.Participants;
import domain.user.Playable;
import domain.user.Player;
import java.util.List;

public class Game {
    
    private final Participants participants;
    
    private final Deck deck;
    
    public Game(final String participantNames, final Deck deck) {
        this.participants = Participants.of(participantNames);
        this.deck = deck;
    }
    
    public void start() {
        for (Playable participant : this.participants) {
            this.deal(participant);
            this.deal(participant);
        }
    }
    
    public void deal(Playable participant) {
        participant.addCard(this.deck.draw());
    }
    
    public GameResult generateGameResult() {
        Dealer dealer = this.participants.getDealer();
        List<Player> players = this.participants.getPlayers();
        GameResult gameResult = new GameResult();
        for (Player player : players) {
            gameResult.accumulate(player, this.comparePlayerWithDealer(player, dealer));
        }
        return gameResult;
    }
    
    private Result comparePlayerWithDealer(Player player, Dealer dealer) {
        if (player.isBust() && !dealer.isBust()) {
            return Result.LOSE;
        }
        if (!player.isBust() && dealer.isBust()) {
            return Result.WIN;
        }
        if (player.isBust() && dealer.isBust()) {
            return Result.DRAW;
        }
        if (player.getScore() > dealer.getScore()) {
            return Result.WIN;
        }
        if (player.getScore() < dealer.getScore()) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }
    
    public List<Player> getPlayers() {
        return this.participants.getPlayers();
    }
    
    public Dealer getDealer() {
        return this.participants.getDealer();
    }
    
    public Participants getParticipants() {
        return this.participants;
    }
}
