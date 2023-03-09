package domain.game;

import domain.Card.Deck;
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
    
    private ResultStatus comparePlayerWithDealer(Player player, Dealer dealer) {
        if (player.isBust() && !dealer.isBust()) {
            return ResultStatus.LOSE;
        }
        if (!player.isBust() && dealer.isBust()) {
            return ResultStatus.WIN;
        }
        if (player.isBust() && dealer.isBust()) {
            return ResultStatus.DRAW;
        }
        if (player.getScore() > dealer.getScore()) {
            return ResultStatus.WIN;
        }
        if (player.getScore() < dealer.getScore()) {
            return ResultStatus.LOSE;
        }
        return ResultStatus.DRAW;
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
