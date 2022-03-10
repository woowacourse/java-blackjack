package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Player extends Gamer{

    private static final int BLACKJACK_NUMBER = 21;

    public Player(String name) {
        super(name);
    }

    public static List<Player> of(List<String> playerNames) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(playerName));
        }
        return players;
    }

    public int compareCardsSumTo(int sum) {
        if (isBust()) {
            return -1;
        }

        if (isDealerBust(sum)) {
            return 1;
        }

        return Integer.compare(this.getCardGroupSum(), sum);
    }

    private boolean isDealerBust(int sum) {
        return sum > BLACKJACK_NUMBER;
    }
}
