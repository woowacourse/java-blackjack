package domain.player;

public record PlayerName(
        String name
) {

    public static PlayerName from(String name) {
        validateNameIsBlank(name);
        validateNameLength(name);
        return new PlayerName(name);
    }

    private static void validateNameIsBlank(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름은 빈칸일 수 없습니다.");
        }
    }

    private static void validateNameLength(String name) {
        if(name.length() > 5) {
            throw new IllegalArgumentException("이름 1자 이상, 5자이하의 문자입니다.");
        }
    }

}
