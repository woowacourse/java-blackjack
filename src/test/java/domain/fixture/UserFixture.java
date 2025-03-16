package domain.fixture;

import domain.player.Users;
import java.util.LinkedHashMap;

public class UserFixture {

    public static final LinkedHashMap<String, Integer> ZERO_USERS_MAP = new LinkedHashMap<>();

    public static final LinkedHashMap<String, Integer> FIVE_USERS_MAP = new LinkedHashMap<>() {{
        put("헤일러", 1000);
        put("라젤", 100000);
        put("새로이", 3000);
        put("칼리", 5000);
        put("링크", 3000);
    }};

    public static final LinkedHashMap<String, Integer> SIX_USERS_MAP = new LinkedHashMap<>() {{
        put("헤일러", 1000);
        put("라젤", 100000);
        put("새로이", 3000);
        put("칼리", 5000);
        put("링크", 3000);
        put("앤지", 5000);
    }};

    public static final Users FIVE_USERS = Users.from(
            new LinkedHashMap<>() {{
                put("헤일러", 1000);
                put("라젤", 100000);
                put("새로이", 3000);
                put("칼리", 5000);
                put("링크", 3000);
            }}
    );
}
