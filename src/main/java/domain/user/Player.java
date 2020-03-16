package domain.user;

public class Player extends User {
    private static final String NULL_OR_EMPTY_NAME_ERROR_MESSAGE = "이름이 비어있습니다.";
    private Name name;

    public Player(String name) {
        if (name == null || name.isEmpty()) {
            throw new NullPointerException(NULL_OR_EMPTY_NAME_ERROR_MESSAGE);
        }
        this.name = new Name(name);
    }

    public String getName() {
        return name.getName();
    }
}
