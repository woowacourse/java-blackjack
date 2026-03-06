package domain.player;

public record PlayerName(
        String name
) {

    private static final String BLANK_NAME_ERROR = "이름은 빈칸일 수 없습니다.";
    private static final String NAME_LENGTH_ERROR = ("이름은 1자 이상, 5자 이하의 문자입니다.");

    public static PlayerName from(String name) {
        validateNameIsBlank(name);
        validateNameLength(name);
        return new PlayerName(name);
    }

    private static void validateNameIsBlank(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(BLANK_NAME_ERROR);
        }
    }

    private static void validateNameLength(String name) {
        if(name.length() > 5) {
            throw new IllegalArgumentException(NAME_LENGTH_ERROR);
        }
    }

}
