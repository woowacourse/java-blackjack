package domain;

import domain.user.PlayersInfo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestUtils {

    public static PlayersInfo createPlayersInfo(List<String> names, int money) {
        Map<String, Integer> playerInfo = names.stream()
                .collect(Collectors.toMap(Function.identity(), name -> money,
                        (e1, e2) -> {
                            throw new AssertionError("중복된 키가 있습니다.");
                        },
                        LinkedHashMap::new));

        return PlayersInfo.of(playerInfo);
    }
}
