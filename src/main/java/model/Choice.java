package model;

public enum Choice {
    YES("[yY]", true),
    NO("[nN]", false);

    Choice(String originalText, boolean choice) {
        this.choice = choice;
        this.originalText = originalText;
    }

    private final String originalText;
    private final boolean choice;

    public static Choice from(String commandText) {
        if (commandText.matches(YES.originalText)){
            return YES;
        }
        if (commandText.matches(NO.originalText)){
            return NO;
        }
        throw new IllegalArgumentException("[ERROR] y 혹은 n만 입력 가능합니다.");
    }

    public boolean isYes() {
        return choice;
    }
}
