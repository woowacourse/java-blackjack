package blackjack.domain.player;

public class Name {

    public static final String EMPTY_NAME = "";
    public static final String SPACE = " ";

    private final String name;

    private Name(String name) {
        this.name = removeEmptySpace(name);
    }

    public static Name of(String name) {
        return new Name(name);
    }

    private static String removeEmptySpace(String inputName) {
        return inputName.trim().replace(SPACE, EMPTY_NAME);
    }

    public String getName() {
        return name;
    }
}
