package domain.gamestate;

public class Lose implements GameState{

    @Override
    public double calculate(int betAmount) {
        return betAmount * -1;
    }
}
