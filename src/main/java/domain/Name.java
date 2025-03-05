package domain;

public class Name {

    private final String name;

    public Name(String name) {
        validateBlank(name);
        this.name = name;
    }

    private void validateBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름은 빈값을 입력 할 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }
}
