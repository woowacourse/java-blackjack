package manager;

import java.util.Arrays;

public enum Intent {

    Y("y"),
    N("n");

    private final String displayName;

    Intent(String displayName) {
        this.displayName = displayName;
    }

    public static Intent from(String intent) {
        return Arrays.stream(Intent.values())
                .filter(o -> o.displayName.equals(intent))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y 또는 n 만 입력 가능합니다."));
    }
}
