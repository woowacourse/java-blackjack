package blackjack.domain;

import static blackjack.domain.Result.DRAW;
import static blackjack.domain.Result.LOSE;
import static blackjack.domain.Result.WIN;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<Player, Result> decideResults(int dealerScore) {
        Map<Player, Result> results = new HashMap<>();

        for (Player player : players) {
            compareScore(results, dealerScore, player);
        }

        return results;
    }

    private void compareScore(final Map<Player, Result> results, final int dealerScore, final Player player) {
        if (dealerScore < player.calculateTotalScore()) {
            results.put(player, WIN);
        }
        if (dealerScore > player.calculateTotalScore()) {
            results.put(player, LOSE);
        }
        if (dealerScore == player.calculateTotalScore()) {
            results.put(player, DRAW);
        }
    }
}
