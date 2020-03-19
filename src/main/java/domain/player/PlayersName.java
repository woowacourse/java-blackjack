package domain.player;

import java.util.*;

public class PlayersName {
    private static final String DELIMITER = ",";

    private List<String> playerName;

    public PlayersName(String playerName) {
        validateEmptyName(playerName);
        validateDuplicatedName(playerName);
        this.playerName = new ArrayList<>(Arrays.asList(playerName.split(DELIMITER)));
    }

    private void validateEmptyName(String playersName) {
        if (Arrays.stream(playersName.split(DELIMITER,-1)).anyMatch(String::isEmpty)) {
            throw new IllegalArgumentException("플레이어 이름을 잘못 입력하였습니다.");
        }
    }

    private void validateDuplicatedName(String inputPlayersName) {
        String[] playersName = inputPlayersName.split(",");
        Set<String> deleteDuplicateNames = new HashSet<>(Arrays.asList(playersName));

        if (deleteDuplicateNames.size() != playersName.length) {
            throw new IllegalArgumentException("중복된 이름입력이 불가합니다.");
        }
    }

    public List<String> getPlayerName() {
        return Collections.unmodifiableList(playerName);
    }
}
