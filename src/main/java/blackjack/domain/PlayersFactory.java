package blackjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersFactory {

    public static Players from(String[] names) {
        validateLength(names);
        return createPlayers(names);
    }

    private static Players createPlayers(final String[] names) {
        List<Player> players = Arrays.stream(names)
                .map(String::trim)
                .map(Player::new)
                .collect(Collectors.toList());

        return new Players(players);
    }

    private static void validateLength(String[] names) {
        if (names.length < 2 || names.length > 8) {
            throw new IllegalArgumentException("[ERROR] 참가자의 수는 최소 2명에서 최대 8명이어야 합니다.");
        }
    }
}
