package domain.player;

import domain.card.Card;
import domain.card.PlayingCards;

import java.util.List;
import java.util.Objects;

public abstract class Player {
    private final PlayingCards playingCards;
    private final PlayerName name;

    protected Player(String name) {
        this.playingCards = new PlayingCards();
        this.name = new PlayerName(name);
    }
    
    public void addCard(Card card) {
        playingCards.addCard(card);
    }
    
    public GameResult battleResult(Player otherPlayer) {
        if (isBurst() || otherPlayer.isBurst()) {
            return decideGameResultWithBurst(otherPlayer);
        }
        
        int totalScore = getTotalScore();
        int totalScoreOfOtherPlayer = otherPlayer.getTotalScore();
        return decideGameResultWithScore(totalScore, totalScoreOfOtherPlayer);
    }
    
    private GameResult decideGameResultWithBurst(Player otherPlayer) {
        if (isBurst() && otherPlayer.isBurst()) {
            return GameResult.DRAW;
        }
        
        if (isBurst()) {
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
        return playingCards.getTotalScore();
    }
    
    public boolean isBurst() {
        return playingCards.isBurst(getTotalScore());
    }
    
    public List<Card> getCards() {
        return playingCards.getCards();
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
        return Objects.equals(playingCards, player.playingCards) && Objects.equals(name, player.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(playingCards, name);
    }
}
