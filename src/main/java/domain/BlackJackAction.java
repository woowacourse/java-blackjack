package domain;

public enum BlackJackAction {
    HIT, STAND;

    private static final String YES = "y";
    private static final String NO = "n";

    public static BlackJackAction getActionByInput(String input) {
        if (!isValid(input)) {
            throw new IllegalArgumentException();
        }
        if (input.equals("y")) {
            return HIT;
        }
        return STAND;
    }

    private static boolean isValid(String input) {
        return input.equals(YES) || input.equals(NO);
    }
}
