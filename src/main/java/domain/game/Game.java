package domain.game;

import domain.card.Deck;
import domain.result.ResultStatus;
import domain.result.StatusResult;
import domain.user.Dealer;
import domain.user.GameMember;
import domain.user.Playable;
import domain.user.Player;
import java.util.List;

public class Game {
    
    private final GameMember gameMember;
    
    private final Deck deck;
    
    
    public Game(final String participantNames, final Deck deck) {
        this.gameMember = GameMember.of(participantNames);
        this.deck = deck;
    }
    
    public Game(final GameMember gameMember, final Deck deck) {
        this.gameMember = gameMember;
        this.deck = deck;
    }
    
    public void start() {
        for (Playable member : this.gameMember) {
            this.deal(member);
            this.deal(member);
        }
    }
    
    public void deal(Playable participant) {
        participant.addCard(this.deck.draw());
    }
    
    public StatusResult generateGameResult() {
        Dealer dealer = this.getDealer();
        List<Player> players = this.getPlayers();
        StatusResult statusResult = new StatusResult();
        for (Player player : players) {
            statusResult.accumulate(player, this.comparePlayerWithDealer(player, dealer));
        }
        return statusResult;
    }
    
    public Dealer getDealer() {
        return this.gameMember.getDealer();
    }
    
    public List<Player> getPlayers() {
        return this.gameMember.getPlayers();
    }
    
    private ResultStatus comparePlayerWithDealer(Player player, Dealer dealer) {
        if (player.isBust() && !dealer.isBust()) {
            return ResultStatus.of(false, false, false);
        }
        if (!player.isBust() && dealer.isBust()) {
            return ResultStatus.of(true, false, player.isBlackJack());
        }
        if (player.isBust() && dealer.isBust()) {
            return ResultStatus.of(false, true, false);
        }
        if (player.getScore() > dealer.getScore()) {
            return ResultStatus.of(true, false, player.isBlackJack());
        }
        if (player.getScore() < dealer.getScore()) {
            return ResultStatus.of(false, false, false);
        }
        // Same score but player is blackjack
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            return ResultStatus.of(true, false, true);
        }
        // Same score but dealer is blackjack
        if (!player.isBlackJack() && dealer.isBlackJack()) {
            return ResultStatus.of(false, false, false);
        }
        return ResultStatus.of(false, true, false);
    }
    
    
    public GameMember getParticipants() {
        return this.gameMember;
    }
}
