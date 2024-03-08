package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(final List<Player> players) {

        validate(players);
        this.players = players;

    }

    private void validate(final List<Player> players) {
        validatePlayerNumbers(players);
        //2
        validateDuplicate(players);
    }

    private static void validatePlayerNumbers(final List<Player> players) {
        if(isInvalidPlayersNumber(players)){
            throw new IllegalArgumentException("플레이어의 수는 8명을 초과할 수 없습니다.");
        }
    }

    private boolean hasDuplicatePlayers(final List<Player> players) {
        final List<Player> distinctPlayers = players.stream().distinct().toList();
        return distinctPlayers.size() != players.size();
    }

    private static boolean isInvalidPlayersNumber(final List<Player> players) {
        return players.size() > 8;
    }

    private void validateDuplicate(final List<Player> players) {
        if(hasDuplicatePlayers(players)){
            throw new IllegalArgumentException("플레이어의 이름은 중복될 수 없습니다");
        }
    }

    public static Players from(final String[] names) {
        return new Players(Arrays.stream(names).map(name -> new Participant(new Name(name))).collect(Collectors.toList()));
    }

    public Player getDealer() {
        return players.stream().filter(player -> player.getName().equals("딜러")).findAny().orElseThrow();
    }

    public List<Player> getParticipants() {
        return players.stream().filter(player -> !player.getName().equals("딜러")).collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }
}
