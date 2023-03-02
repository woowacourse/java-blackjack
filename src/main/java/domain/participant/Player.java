package domain.participant;

public class Player extends Participant {
    private static final String ERROR_NAME_LENGTH = "[ERROR] 플레이어의 이름은 2 ~ 10 글자여야 합니다.";
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 10;

    private final String name;

    private Player(String name) {
        super();
        this.name = name.trim();
    }

    public static Player from(String name) {
        validate(name);
        return new Player(name);
    }

    private static void validate(String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(ERROR_NAME_LENGTH);
        }
    }
}
