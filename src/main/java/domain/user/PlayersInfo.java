package domain.user;

import domain.card.Deck;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PlayersInfo {

    private final Map<Player, BettingMoney> playersInfo;

    private PlayersInfo(Map<String, Integer> playersInfo) {
        this.playersInfo = playersInfo.entrySet()
            .stream()
            .collect(Collectors.toMap(entry -> new Player(entry.getKey()),
                    entry -> new BettingMoney(entry.getValue()),
                    (e1, e2) -> e1, LinkedHashMap::new));
    }

    public static PlayersInfo of(Map<String, Integer> playerNames) {
        return new PlayersInfo(playerNames);
    }

    public void draw(Deck deck) {
        playersInfo.forEach(
                (player, bettingMoney) -> player.draw(deck)
        );
    }

    public Map<Player, Integer> calculatePoint() {
        return playersInfo.keySet()
                .stream()
                .collect(Collectors.toMap(Function.identity(), Player::calculatePoint,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    public Map<Player, Integer> calculateProfit(Dealer dealer) {
        return playersInfo.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> entry.getValue().calculateProfit(dealer, entry.getKey()),
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(new ArrayList<>(playersInfo.keySet()));
    }

    public Map<Player, BettingMoney> getPlayersInfo() {
        return Collections.unmodifiableMap(playersInfo);
    }
}
