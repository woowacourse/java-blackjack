package blackjack.domain.game;

import blackjack.domain.participant.Money;
import blackjack.domain.participant.Player;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BettingPlayers {
    private final Map<Player, Money> players = new ConcurrentHashMap<>();

    public BettingPlayers(final List<Player> players, final List<Money> moneys) {
        validate(players, moneys);
        final int playerSize = players.size();
        for (int index = 0; index < playerSize; index++) {
            final Player currentPlayer = players.get(index);
            final Money currentMoney = moneys.get(index);
            this.players.put(currentPlayer, currentMoney);
        }
    }

    private void validate(final List<Player> players, final List<Money> moneys) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("플레이어 목록은 비어있을 수 없습니다.");
        }
        if (moneys.isEmpty()) {
            throw new IllegalArgumentException("돈 목록은 비어있을 수 없습니다.");
        }
    }
}
