package domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class UserPick {
    private Map<User, Cards> userPick;

    public UserPick(List<String> names) {
        HashSet<String> distinctNames = new HashSet<>(names);
        if (names.size() < 1 || names.size() > 7) {
            throw new IllegalArgumentException("유저는 1명 이상 7명 이하로 등록해야 합니다.");
        }
        if (distinctNames.size() != names.size()) {
//            throw new IllegalArgumentException("유저는 ");
        }
    }
}
