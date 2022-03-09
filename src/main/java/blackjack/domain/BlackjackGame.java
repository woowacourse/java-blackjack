package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {

    private final Cards cards;
    private final List<Player> blackjackPlayers;

    public BlackjackGame(List<String> playerNames) {
        this.cards = new Cards(new CardShuffleMachine());
        this.blackjackPlayers = new ArrayList<>();
        blackjackPlayers.add(new Dealer());
        for (String playerName : playerNames) {
            blackjackPlayers.add(new Guest(playerName));
        }
    }

    public List<Player> initGames() {
        for (Player blackjackPlayer : blackjackPlayers) {
            blackjackPlayer.addCard(cards.assignCard());
            blackjackPlayer.addCard(cards.assignCard());
        }
        return blackjackPlayers;
    }
}
