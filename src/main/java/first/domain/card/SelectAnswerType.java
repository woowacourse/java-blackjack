package first.domain.card;

import java.util.Arrays;

public enum SelectAnswerType {
    YES("y", true),
    NO("n", false);

    private final String value;
    private final boolean isYes;

    SelectAnswerType(String value, boolean isYes) {
        this.value = value;
        this.isYes = isYes;
    }

    public static SelectAnswerType of(String yesno) {
        return Arrays.stream(SelectAnswerType.values())
                .filter(eachSelectAnswerType -> yesno.equals(eachSelectAnswerType.value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("y와 n이 매개변수로 들어와야합니다."));
    }

    public boolean getYes() {
        return isYes;
    }
}
