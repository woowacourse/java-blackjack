package dto;

import java.util.*;

public class RequestPlayerNamesDTO {
    private static final String DELIMITER = ",";

    private List<String> playerNames;

    public RequestPlayerNamesDTO(String playerName) {
        String[] playerNames = playerName.split(DELIMITER);
        validateEmptyNames(playerNames);
        List<String> playerNameList = new ArrayList<>(Arrays.asList(playerNames));
        validateDuplicateNames(playerNameList);

        this.playerNames = playerNameList;
    }

    private void validateEmptyNames(String[] playerNames) {
        for (String name : playerNames) {
            validateEmptyName(name);
        }
    }

    private void validateEmptyName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("비어있는 이름을 입력하였습니다.");
        }
    }

    private void validateDuplicateNames(List<String> playerNameList) {
        Set<String> duplicatePlayerNames = new HashSet(playerNameList);
        if (duplicatePlayerNames.size() != playerNameList.size()) {
            throw new IllegalArgumentException("중복된 이름을 입력하였습니다.");
        }
    }

    public List<String> getPlayerName() {
        return Collections.unmodifiableList(this.playerNames);
    }
}
