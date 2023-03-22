package user;

public enum PlayerAction {
    HIT, STAND;

    private static final String YES = "y";
    private static final String NO = "n";

    public static PlayerAction getActionByInput(String input) {
        if (!isValid(input)) {
            throw new IllegalArgumentException("y나 n으로 입력해 주세요.");
        }
        if (input.equals(YES)) {
            return HIT;
        }
        return STAND;
    }

    private static boolean isValid(String input) {
        return input.equals(YES) || input.equals(NO);
    }
}
