package domain.game;

public enum Requests {
    HIT("y"),
    STAY("n");

    private final String request;

    Requests(String request) {
        this.request = request;
    }

    public static boolean isHit(String input) {
        return input.equals(HIT.request);
    }

    public static boolean isStay(String input) {
        return input.equals(STAY.request);
    }
}
