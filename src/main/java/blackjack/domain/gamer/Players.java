package blackjack.domain.gamer;

import blackjack.domain.card.Deck;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Players {

    private static final int MIN_PLAYER_COUNT = 2;
    private static final int MAX_PLAYER_COUNT = 8;

    private final Map<Player, Money> playerBetAmountMap;

    public Players(Map<Player, Money> playerBetAmountMap) {
        validatePlayerCount(playerBetAmountMap.size());
        this.playerBetAmountMap = playerBetAmountMap;
    }

    private void validatePlayerCount(int count) {
        if (count < MIN_PLAYER_COUNT || count > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException(
                    "플레이어는 최소 " + MIN_PLAYER_COUNT + "명에서 최대 " + MAX_PLAYER_COUNT + "명까지 가능합니다."
            );
        }
    }

    public void initAllPlayersCard(Deck deck, int initialCardCount) {
        playerBetAmountMap.keySet().forEach(player -> player.initCard(deck, initialCardCount));
    }

    public Money getBetAmount(Player player) {
        return playerBetAmountMap.get(player);
    }

    public Collection<Player> getPlayers() {
        return List.copyOf(playerBetAmountMap.keySet());
    }
}
