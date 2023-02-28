public class PlayerName {
    private final String name;
    public PlayerName(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        validateIsNull(name);
    }

    private void validateIsNull(String name) {
        if(name == null){
            throw new NullPointerException("플레이어 이름은 null을 허용하지 않습니다.");
        }
    }
}
