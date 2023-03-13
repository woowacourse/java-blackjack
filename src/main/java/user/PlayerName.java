package user;

public class PlayerName {

    private final String name;

    public PlayerName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름을 입력해 주세요");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
