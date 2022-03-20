package blackjack.domain.game;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;

import blackjack.domain.Name;

public enum PlayRecord {

    WIN,
    PUSH,
    LOSS,
    BLACKJACK;

    static Map<Name, PlayRecord> createPlayRecords(List<Player> players, Dealer dealer) {
        return List.copyOf(players).stream()
            .collect(toUnmodifiableMap(Player::getName,
                dealer::playerRecord));
    }
}
