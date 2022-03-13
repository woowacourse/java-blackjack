package blackjack.domain.machine;

public enum Hit {

    Y("y"),
    N("n"),
    ;

    private final String userResponse;

    Hit(String userResponse) {
        this.userResponse = userResponse;
    }

    public static boolean isYes(String userResponse) {
        return Y.userResponse.equals(userResponse);
    }
}
