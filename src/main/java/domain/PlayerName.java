package domain;

public class PlayerName {

    private static final String BLANK_ERROR_GUIDE_MESSAGE = "[ERROR] 플레이어의 이름은 빈 칸일 수 없습니다.";
    private static final String NULL_ERROR_GUIDE_MESSAGE = "[ERROR] 플레이어의 이름은 null일 수 없습니다.";
    private static final String EMPTY_ERROR_GUIDE_MESSAGE = "[ERROR] 플레이가어의 이름은 Empty일 수 없습니다.";

    private final String name;

    public PlayerName(String name) {
        validatePlayerName(name);
        this.name = name;
    }

    private void validatePlayerName(String name) {
        validateNull(name);
        validateBlank(name);
        validateEmpty(name);
    }

    private void validateNull(String name) {
        if (name == null) {
            throw new IllegalArgumentException(NULL_ERROR_GUIDE_MESSAGE);
        }
    }

    private void validateBlank(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(BLANK_ERROR_GUIDE_MESSAGE);
        }
    }

    private void validateEmpty(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_ERROR_GUIDE_MESSAGE);
        }
    }

    public String getName() {
        return name;
    }

}
