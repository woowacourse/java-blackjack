package domain.fixture;

import domain.player.Users;
import java.util.Map;

public class UserFixture {

    public static final Map<String, Integer> ZERO_USERS_MAP = Map.of();

    public static final Map<String, Integer> FIVE_USERS_MAP = Map.ofEntries(
            Map.entry("헤일러", 1000),
            Map.entry("라젤", 100000),
            Map.entry("새로이", 3000),
            Map.entry("칼리", 5000),
            Map.entry("링크", 3000)
    );

    public static final Map<String, Integer> SIX_USERS_MAP = Map.ofEntries(
            Map.entry("헤일러", 1000),
            Map.entry("라젤", 100000),
            Map.entry("새로이", 3000),
            Map.entry("칼리", 5000),
            Map.entry("링크", 3000),
            Map.entry("앤지", 3000)
    );

    public static final Users FIVE_USERS = Users.from(
            Map.ofEntries(
                    Map.entry("헤일러", 1000),
                    Map.entry("라젤", 100000),
                    Map.entry("새로이", 3000),
                    Map.entry("칼리", 5000),
                    Map.entry("링크", 3000)
            )
    );
}
