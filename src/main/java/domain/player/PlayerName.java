package domain.player;

public class PlayerName {

    private static final String BLANK_ERROR_GUIDE_MESSAGE = "[ERROR] 플레이어의 이름은 빈 칸일 수 없습니다.";

    private final String name;

    public PlayerName(final String name) {
        validatePlayerName(name);
        this.name = name;
    }

    private void validatePlayerName(final String name) {
        validateBlank(name);
    }

    private void validateBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(BLANK_ERROR_GUIDE_MESSAGE);
        }
    }

    public String getName() {
        return name;
    }
}
