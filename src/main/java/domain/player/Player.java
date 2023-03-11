package domain.player;

import domain.card.Card;
import domain.card.Score;
import domain.player.state.Ready;
import domain.player.state.State;

import java.util.List;
import java.util.Objects;

public abstract class Player {
    private State state;
    private final PlayerName name;

    protected Player(String name) {
        this.state = new Ready();
        this.name = new PlayerName(name);
    }
    
    public abstract boolean isDealer();
    
    public abstract double calculateProfit(double betAmount);
    
    public abstract boolean isFinished();
    
    public void draw(Card card) {
        state = state.draw(card);
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
    
    public void drawStop() {
        state = state.drawStop();
    }
    
    public String getName() {
        return this.name.getName();
    }
    
    protected State getState() {
        return state;
    }
    
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
