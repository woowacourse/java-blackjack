package domain.player;

import domain.card.Card;
import domain.card.Score;
import domain.state.Ready;
import domain.state.State;

import java.util.List;
import java.util.Objects;

public abstract class Player {
    private State state;
    private final PlayerName name;

    protected Player(String name) {
        this.state = new Ready();
        this.name = new PlayerName(name);
    }
    
    public void draw(Card card) {
        state = state.draw(card);
    }
    
    public GameResult battleResult(Player otherPlayer) {
        if (isBust() || otherPlayer.isBust()) {
            return decideGameResultWithBust(otherPlayer);
        }
        
        Score totalScore = getTotalScore();
        Score totalScoreOfOtherPlayer = otherPlayer.getTotalScore();
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
    
    private GameResult decideGameResultWithScore(Score totalScore, Score totalScoreOfOtherPlayer) {
        if (totalScore.isOverThen(totalScoreOfOtherPlayer)) {
            return GameResult.WIN;
        }
        
        if (totalScore.isLessThen(totalScoreOfOtherPlayer)) {
            return GameResult.LOSE;
        }
        
        return GameResult.DRAW;
    }
    
    public boolean isBust() {
        return state.score().isBust();
    }
    
    public Score getTotalScore() {
        return state.score();
    }
    
    public List<Card> getCards() {
        return state.getCards();
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
        return Objects.equals(state, player.state) && Objects.equals(name, player.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(state, name);
    }
}
