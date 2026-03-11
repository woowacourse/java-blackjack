package domain;

import meesage.ErrorMessage;

public enum HitDecision {

    YES("y"),
    NO("n");

    private final String value;

    HitDecision(String value) {
        this.value = value;
    }

    public static HitDecision from(String answer) {
        for (HitDecision value : values()) {
            if (value.value.equals(answer)) {
                return value;
            }
        }
        throw new IllegalArgumentException(ErrorMessage.INVALID_YES_OR_NO_INPUT.getMessage());
    }
}
