package domain.game;

import domain.card.Deck;
import domain.result.ProfitResult;
import domain.user.Dealer;
import domain.user.GameMember;
import domain.user.Playable;
import domain.user.Player;
import java.util.List;

public class Game {
    
    private final GameMember gameMember;
    
    private final Deck deck;
    
    
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
    
    public ProfitResult generateProfitResult() {
        Dealer dealer = this.getDealer();
        List<Player> players = this.getPlayers();
        ProfitResult profitResult = new ProfitResult();
        for (Player player : players) {
            profitResult.accumulate(player, player.getBet(), this.comparePlayerWithDealer(player, dealer));
        }
        return profitResult;
    }
    
    public Dealer getDealer() {
        return this.gameMember.getDealer();
    }
    
    public List<Player> getPlayers() {
        return this.gameMember.getPlayers();
    }
    
    private GameStatus comparePlayerWithDealer(Player player, Dealer dealer) {
        if (player.isBust() && !dealer.isBust()) {
            return GameStatus.of(false, false, false);
        }
        if (!player.isBust() && dealer.isBust()) {
            return GameStatus.of(true, false, player.isBlackJack());
        }
        if (player.isBust() && dealer.isBust()) {
            return GameStatus.of(false, true, false);
        }
        if (player.getScore() > dealer.getScore()) {
            return GameStatus.of(true, false, player.isBlackJack());
        }
        if (player.getScore() < dealer.getScore()) {
            return GameStatus.of(false, false, false);
        }
        // Same score but player is blackjack
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            return GameStatus.of(true, false, true);
        }
        // Same score but dealer is blackjack
        if (!player.isBlackJack() && dealer.isBlackJack()) {
            return GameStatus.of(false, false, false);
        }
        return GameStatus.of(false, true, false);
    }
    
    public GameMember getParticipants() {
        return this.gameMember;
    }
}
