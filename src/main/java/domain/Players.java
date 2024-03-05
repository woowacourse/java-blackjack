package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Players {

    private static final int MIN_SIZE = 2;
    private static final int MAX_SIZE = 8;

    private final List<Player> names;

    private Players(final List<Player> names) {
        this.names = names;
    }

    public static Players from(final List<String> names) {
        List<Player> players = names.stream()
                .map(name -> new Player(name, Packet.createEmptyPacket()))
                .toList();
        validate(players);
        return new Players(players); //TODO 이름바꾸기
    }

    private static void validate(final List<Player> players) {
        validateSize(players);
        validateDuplicate(players);
    }

    private static void validateSize(final List<Player> players) {
        if (players.size() < MIN_SIZE || MAX_SIZE < players.size()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 참여자 수입니다.");
        }
    }

    private static void validateDuplicate(final List<Player> players) {
        if (players.size() != Set.copyOf(players).size()) {
            throw new IllegalArgumentException("[ERROR] 참여자 이름은 중복될 수 없습니다.");
        }
    }

}
