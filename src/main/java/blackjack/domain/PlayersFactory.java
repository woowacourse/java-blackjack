package blackjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersFactory {

    public static final int MIN_PLAYER_COUNT = 2;
    public static final int MAX_PLAYER_COUNT = 8;

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
        if (names.length < MIN_PLAYER_COUNT || names.length > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 참가자의 수는 최소 2명에서 최대 8명이어야 합니다.");
        }
    }
}
