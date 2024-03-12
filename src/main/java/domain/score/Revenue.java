package domain.score;

import domain.player.Bet;

import java.util.List;

public class Revenue {

    private int value = 0;

    public Revenue() {
    }

    public void calculate(List<Bet> bets) {
        int sum = bets.stream()
                .mapToInt(Bet::getAmount)
                .sum();
        this.value = sum * -1;
    }

    public int getAmount() {
        return value;
    }
}
