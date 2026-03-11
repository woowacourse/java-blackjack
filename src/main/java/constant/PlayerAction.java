package constant;

import exception.ErrorMessage;

public enum PlayerAction {
    HIT("y"),
    STAND("n");

    private final String action;

    PlayerAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public boolean isHit() {
        return this == HIT;
    }

    public boolean isStand() {
        return this == STAND;
    }

    public static PlayerAction from(String input) {
        validate(input);
        if (input.equals(HIT.getAction())) {
            return PlayerAction.HIT;
        }
        return PlayerAction.STAND;
    }

    private static void validate(String input) {
        String normalized = input.strip();
        if (!normalized.equals(PlayerAction.HIT.getAction()) && !normalized
            .equals(PlayerAction.STAND.getAction())) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_YES_NO_INPUT.getMessage());
        }
    }
}
