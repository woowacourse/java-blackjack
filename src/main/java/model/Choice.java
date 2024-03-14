package model;

public enum Choice {
    YES(true), NO(false);

    Choice(boolean choice) {
        this.choice = choice;
    }

    private final boolean choice;

    public static Choice from(Boolean playerChoiceInput) {
        if (Boolean.TRUE.equals(playerChoiceInput)) {
            return YES;
        }
        return NO;
    }

    public boolean isHit() {
        return choice;
    }
}
