package domain.player;

import domain.card.Card;
import domain.card.Hand;

import java.util.List;
import java.util.Objects;

public abstract class Player {
    private final Hand hand;
    private final PlayerName name;

    protected Player(String name) {
        this.hand = new Hand();
        this.name = new PlayerName(name);
    }
    
    public void addCard(Card card) {
        hand.addCard(card);
    }
    
    public GameResult battleResult(Player otherPlayer) {
        if (isBust() || otherPlayer.isBust()) {
            return decideGameResultWithBust(otherPlayer);
        }
        
        int totalScore = getTotalScore();
        int totalScoreOfOtherPlayer = otherPlayer.getTotalScore();
        return decideGameResultWithScore(totalScore, totalScoreOfOtherPlayer);
    }
    
    private GameResult decideGameResultWithBust(Player otherPlayer) {
        if (isBust() && otherPlayer.isBust()) {
            return GameResult.DRAW;
        }
        
        if (isBust()) {
            return GameResult.LOSE;
        }
        
        return GameResult.WIN;
    }
    
    private GameResult decideGameResultWithScore(int totalScore, int totalScoreOfOtherPlayer) {
        if (totalScore > totalScoreOfOtherPlayer) {
            return GameResult.WIN;
        }
        
        if (totalScore < totalScoreOfOtherPlayer) {
            return GameResult.LOSE;
        }
        
        return GameResult.DRAW;
    }
    
    public int getTotalScore() {
        return hand.getTotalScore();
    }
    
    public boolean isBust() {
        return hand.isBust(getTotalScore());
    }
    
    public List<Card> getCards() {
        return hand.getCards();
    }
    
    public String getName() {
        return this.name.getName();
    }
    
    public abstract boolean isDealer();
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(hand, player.hand) && Objects.equals(name, player.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(hand, name);
    }
}
