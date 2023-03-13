package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class Players {// TODO: 2023/03/07 Player를 copy 해서 내보내고 싶다.
    private final List<Player> players;

    private Players(final List<Player> players) {
        validateNotEmpty(players);
        this.players = players;
    }

    public static Players from(final Map<String, Integer> bettingAmounts) {
        return bettingAmounts.keySet()
                             .stream()
                             .map(playerName -> new Player(playerName, bettingAmounts.get(playerName)))
                             .collect(collectingAndThen(toUnmodifiableList(), Players::new));
    }

    private void validateNotEmpty(final List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("플레이어는 1명 이상이어야 합니다.");
        }
    }

    public void receiveCard(final Deck deck) {
        players.forEach(player -> player.receiveCard(deck.draw()));
    }

    public Player getPlayerToDecide() {
        final Player player = players.stream()
                                     .filter(Player::isDrawable)
                                     .findFirst()
                                     .orElseThrow(() -> new IllegalStateException("카드를 받을 수 있는 플레이어가 없습니다."));
        return player;
    }

    public boolean hasAnyPlayerToDeal() {
        return players.stream()
                      .anyMatch(Player::isDrawable);
    }

    public void dealToCurrentPlayer(final Card card) {
        getPlayerToDecide().receiveCard(card);
    }

    public void standCurrentPlayer() {
        getPlayerToDecide().stand();
    }

    public Map<String, Integer> computePlayerEarnings(final Hand dealerHand) {
        return players.stream()
                      .collect(toUnmodifiableMap(Player::name
                              , player -> player.computeEarning(dealerHand)));
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public List<String> getPlayerNames() {
        return players.stream()
                      .map(Player::name)
                      .collect(toUnmodifiableList());
    }
}
