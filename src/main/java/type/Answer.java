package type;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum Answer {

    HIT("y", true),
    STAND("n", false);

    private static final Map<String, Boolean> REQUEST_ANSWER = Arrays.stream(values())
            .collect(Collectors.toMap(Answer::getInput, Answer::isHit));

    private final String input;
    private final boolean isHit;

    Answer(String input, boolean isHit) {
        this.input = input;
        this.isHit = isHit;
    }

    public static boolean isMoreCardRequested(String input) {
        return REQUEST_ANSWER.get(input);
    }

    public static boolean isInputValid(String input) {
        return REQUEST_ANSWER.containsKey(input);
    }

    public String getInput() {
        return input;
    }

    private boolean isHit() {
        return isHit;
    }
}
