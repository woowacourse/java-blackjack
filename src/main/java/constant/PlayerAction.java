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

    public boolean isStand() {
        return this == STAND;
    }

    public static PlayerAction from(String input) {
        String normalized = input.strip();
        for (PlayerAction playerAction : PlayerAction.values()) {
            if (normalized.equals(playerAction.getAction())) {
                return playerAction;
            }
        }
        throw new IllegalArgumentException(ErrorMessage.INVALID_YES_NO_INPUT.getMessage());
    }
}
