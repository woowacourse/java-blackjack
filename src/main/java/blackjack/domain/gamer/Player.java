package blackjack.domain.gamer;

import static blackjack.domain.card.CardGroup.BLACKJACK_NUMBER;

import blackjack.domain.card.Card;
import blackjack.domain.result.Match;
import java.util.ArrayList;
import java.util.List;

public class Player extends Gamer{

    public Player(String name) {
        super(name);
    }

    public Player(String name, List<Card> cards) {
        super(name, cards);
    }

    public static List<Player> of(List<String> playerNames) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(playerName));
        }
        return players;
    }

    public Match compareCardsSumTo(int anotherCardsSum) {
        if (isBust()) {
            return Match.LOSE;
        }

        if (isBust(anotherCardsSum)) {
            return Match.WIN;
        }

        return Match.of(Integer.compare(getScore(), anotherCardsSum));
    }

    private boolean isBust(int sum) {
        return sum > BLACKJACK_NUMBER;
    }
}
