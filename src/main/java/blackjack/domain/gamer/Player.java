package blackjack.domain.gamer;

import static blackjack.domain.card.CardGroup.BLACKJACK_NUMBER;

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

    public int compareCardsSumTo(int anotherCardsSum) {
        if (isBust()) {
            return -1;
        }

        if (isBust(anotherCardsSum)) {
            return 1;
        }

        return Integer.compare(getCardGroupScore(), anotherCardsSum);
    }

    private boolean isBust(int sum) {
        return sum > BLACKJACK_NUMBER;
    }
}
