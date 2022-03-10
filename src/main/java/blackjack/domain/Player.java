package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Player extends Gamer{

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

        return Integer.compare(this.getCardGroupSum(), sum);
    }
}
