package domain.gamestate;

public class BlackJack extends Win{

    @Override
    public double calculate(int betAmount) {
        return betAmount * 1.5;
    }
}
