package blackjack.domain;

import java.util.Collections;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(final List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public int size() {
        return players.size();
    }

    public void receiveSettingCards(List<Card> settingCards) {
        for (int cardIndex = 0, playerIndex = 0; cardIndex < settingCards.size(); cardIndex += 2, playerIndex++) {
            players.get(playerIndex).receiveCard(settingCards.get(cardIndex));
            players.get(playerIndex).receiveCard(settingCards.get(cardIndex + 1));
        }
    }
}
