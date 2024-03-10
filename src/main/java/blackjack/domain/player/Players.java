package blackjack.domain.player;

import java.util.List;

public record Players(Dealer dealer, List<GamePlayer> gamePlayers) {
}
