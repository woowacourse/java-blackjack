package blackjack.model.gamer;

public class Name {

    private final String name;

    public Name(String name) {
        validateNameContainBlank(name);
        validateNameLength(name);
        this.name = name;
    }

    private void validateNameContainBlank(String name) {
        if (name.contains(" ")) {
            throw new IllegalArgumentException("[ERROR] 이름에는 공백이 포함될 수 없습니다.");
        }
    }

    private void validateNameLength(String name) {
        if (name.isEmpty() || name.length() > 10) {
            throw new IllegalArgumentException("[ERROR] 이름 길이는 0~10자만 가능합니다.");
        }
    }

    public String getName() {
        return name;
    }
}
