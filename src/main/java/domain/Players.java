package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Players {
    private static final String INVALID_NAME = "중복된 이름입니다.";

    private List<Player> players = new ArrayList<>();

    public Players(String names) {
        List<String> splitedName = splitName(names);
        validateDuplicatedName(splitedName);
        for (String name : splitName(names)) {
            players.add(new Player(name));
        }
    }

    private void validateDuplicatedName(List<String> splitedName) {
        if (splitedName.size() != splitedName.stream().distinct().count()) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
    }

    private List<String> splitName(String names) {
        return Arrays.asList(names.split(","));
    }
}
