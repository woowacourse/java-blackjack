package domain.gamer;

import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private static final int MINIMUM_PLAYER_RANGE = 2;
    private static final int MAXIMUM_PLAYER_RANGE = 8;
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players settingPlayers(List<String> inputNames) {
        List<Name> playerNames = makePlayerNames(inputNames);
        validate(makePlayerNames(inputNames));

        List<Player> players = makePlayers(playerNames);
        return new Players(players);
    }

    private static List<Name> makePlayerNames(List<String> inputNames) {
        return inputNames.stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    private static List<Player> makePlayers(List<Name> playerNames) {
        return playerNames.stream()
                .map(Player::new)
                .toList();
    }

    private static void validate(List<Name> players) {
        validatePlayerCountRange(players);
        validateHasDuplicateName(players);
    }

    private static void validateHasDuplicateName(List<Name> players) {
        int uniqueNameCount = countPlayerUniqueName(players);

        if (players.size() != uniqueNameCount) {
            throw new IllegalArgumentException("참가자는 중복된 이름을 가질 수 없습니다.");
        }
    }

    private static int countPlayerUniqueName(List<Name> players) {
        return players.stream()
                .map(Name::getValue)
                .collect(Collectors.toSet())
                .size();
    }

    private static void validatePlayerCountRange(List<Name> players) {
        if (players.size() < MINIMUM_PLAYER_RANGE || MAXIMUM_PLAYER_RANGE < players.size()) {
            throw new IllegalArgumentException(
                    "참가자의 인원은 최소 " + MINIMUM_PLAYER_RANGE + "에서 최대 " + MAXIMUM_PLAYER_RANGE + "명 까지 가능합니다.");
        }
    }

    public void play(PlayerTurn playerTurn, Dealer dealer) {
        for (Player player : players) {
            playerTurn.playTurn(player, dealer);
        }
    }

    public List<Name> getNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Boolean> getResult(int dealerScore) {
        return players.stream()
                .map(player -> player.getMaxGameScore() > dealerScore)
                .collect(Collectors.toList());
    }
}
