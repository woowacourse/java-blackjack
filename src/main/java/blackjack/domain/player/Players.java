package blackjack.domain.player;

import java.util.List;

public record Players(Dealer dealer, List<GamePlayer> gamePlayers) {
    private static final Integer MAX_SIZE = 7;

    public Players {
        validateSize(gamePlayers);
    }

    private static void validateSize(final List<GamePlayer> gamePlayers) {
        if (gamePlayers.size() > MAX_SIZE) {
            throw new IllegalArgumentException(String.format("게임 플레이어는 %d명 까지만 가능합니다.", MAX_SIZE));
        }
    }
}
