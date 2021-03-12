package blackjack.domain.user;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.UserDeck;
import blackjack.domain.money.Money;
import blackjack.exception.BlackJackException;
import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players = new ArrayList<>();

    public Players(CardDeck cardDeck, List<String> playerNames, List<Money> betCapital) {
        for (int i =0 ; i < playerNames.size(); i++) {
            UserDeck initialRandomUserDeck = cardDeck.generateInitialUserDeck();
            String playerName = playerNames.get(i);
            Money betMoney = betCapital.get(i);
            players.add(new Player(playerName, initialRandomUserDeck, betMoney));
        }
    }

    public int size() {
        return players.size();
    }

    public Player getPlayer(int playerIndex) {
        return players.get(playerIndex);
    }

    public List<Player> getRawPlayers() {
        return players;
    }
}
