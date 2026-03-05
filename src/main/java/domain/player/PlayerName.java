package domain.player;

public record PlayerName(
        String name
) {

    public static PlayerName from(String name) {
        validateNameLength(name);
        return new PlayerName(name);
    }

    private static void validateNameLength(String name) {
        if(name.length() > 5) {
            throw new IllegalArgumentException();
        }
    }

}
