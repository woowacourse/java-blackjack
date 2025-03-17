package view;

import java.util.HashSet;
import java.util.List;

public class Validator {

    public final static int MAX_PLAYER = 7;

    public static void validateNames(List<String> names) {
        HashSet<String> distinctNames = new HashSet<>(names);
        if (names.isEmpty() || names.size() > MAX_PLAYER) {
            throw new IllegalArgumentException("유저는 1명 이상 7명 이하로 등록해야 합니다.");
        }
        if (distinctNames.size() != names.size()) {
            throw new IllegalArgumentException("유저는 중복될 수 없습니다.");
        }
    }
}
