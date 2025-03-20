package view;

import java.util.Arrays;
import java.util.function.Predicate;

public enum Intent {

    Y("y", intent -> intent.equals("y")),
    N("n", intent -> false);

    private final String displayName;
    private final Predicate<String> predicate;

    Intent(String displayName, Predicate<String> predicate) {
        this.displayName = displayName;
        this.predicate = predicate;
    }

    public static Intent from(String intent) {
        return Arrays.stream(Intent.values())
                .filter(o -> o.displayName.equals(intent))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y 또는 n 만 입력 가능합니다."));
    }
}
