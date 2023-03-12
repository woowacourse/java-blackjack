package domain.gamestate;

public class Win implements GameState {

    @Override
    public double calculate(int betAmount) {
        return betAmount;
    }
}
