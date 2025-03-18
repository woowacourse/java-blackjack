package blackjack.model.participants;

import java.util.ArrayList;
import java.util.List;
import blackjack.model.bettings.Wager;
import blackjack.model.cards.Hand;

public class Players {
    private static final int INDEX_START = 0;
    private final List<Player> players;

    public Players() {
        players = new ArrayList<>();
    }

    public void initializePlayersWithHand(Dealer dealer, List<String> playerNames, List<Integer> playerWagers) {
        for (int i = INDEX_START; i < playerNames.size(); i++) {
            String name = playerNames.get(i);
            int wager = playerWagers.get(i);
            Hand hand = dealer.produceHand();

            players.add(new Player(new Name(name), new Wager(wager), hand));
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
