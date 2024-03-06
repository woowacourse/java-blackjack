package model.player;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.card.Cards;

public class Players {

    private final List<Player> players;
    private final Cards cards;

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
        Player foundPlayer = players.stream()
                .filter(player -> player.isSameName(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("참가자가 존재하지 않습니다."));
        foundPlayer.addCards(cards.selectRandomCards(cardCount));
    }

    public Map<Player, Integer> sumCardNumbersWithoutDealer() { //TODO Player(리스트)만 넘겨줘도 되는 거 아닌가?
            return players.stream().filter(player -> !player.isDealer())
                .collect(Collectors.toMap(
                        player -> player,
                        Player::sumCardNumbers
                ));
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
