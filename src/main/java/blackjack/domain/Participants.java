package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Participants {

    private final List<Player> blackjackPlayers;

    public Participants(List<String> playerNames) {
        this.blackjackPlayers = new ArrayList<>();
        blackjackPlayers.add(new Dealer());
        for (String playerName : playerNames) {
            blackjackPlayers.add(new Guest(playerName));
        }
    }

    public List<Player> startWithTwoCards(Cards cards) {
        for (Player blackjackPlayer : blackjackPlayers) {
            blackjackPlayer.addCard(cards.assignCard());
            blackjackPlayer.addCard(cards.assignCard());
        }
        return blackjackPlayers;
    }
}
