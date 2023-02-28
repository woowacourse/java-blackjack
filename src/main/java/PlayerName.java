public class PlayerName {
    private static final int MAX_LENGTH = 5;
    private final String name;
    public PlayerName(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        validateIsNull(name);
        validateLength(name);
    }

    private void validateIsNull(String name) {
        if(name == null){
            throw new NullPointerException("플레이어 이름은 null을 허용하지 않습니다.");
        }
    }

    private void validateLength(String name) {
        if(name.isBlank() || name.length() > MAX_LENGTH){
            throw new IllegalArgumentException("이름 길이는 1자 이상 5자 이하여야 합니다.");
        }
    }
}
