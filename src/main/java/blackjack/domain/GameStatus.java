package blackjack.domain;

public class GameStatus {
    private boolean isStart;

    public GameStatus(boolean isStart) {
        this.isStart = isStart;
    }

    public boolean isStart(){
        return this.isStart;
    }
}
