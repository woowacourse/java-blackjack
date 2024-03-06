package model.player;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.card.Cards;

public class Players {

    private final List<Player> players;
    private Cards cards;

    public Players(List<Player> players, Cards cards) {
        validateOneDealer(players);
        this.players = players;
        this.cards = cards;
    }

    private void validateOneDealer(List<Player> players) {
        int count = (int) players.stream()
                .filter(Player::isDealer)
                .count();
        if (count != 1) {
            throw new IllegalArgumentException("딜러는 한 명만 있어야 합니다.");
        }
    }

    public void offerCardToPlayers(int cardCount) {
        for (Player player : players) {
            player.addCards(cards.selectRandomCards(cardCount));
        }
    }

    public void offerCardToPlayer(String name, int cardCount) {
        for (Player player : players) {
            if (player.isSameName(name)) {
                player.addCards(cards.selectRandomCards(cardCount));
                return;
            }
        }
    }

    public Map<Player, Integer> sumCardNumbersWithoutDealer() {
        Map<Player, Integer> sumPlayers = new LinkedHashMap<>();
        for (Player player : players) {
            if (!player.isDealer()) {
                sumPlayers.put(player, player.sumCardNumbers());
            }
        }

        return Collections.unmodifiableMap(sumPlayers);
    }

    public Player getDealer() {
        return players.stream()
                .filter(Player::isDealer)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
