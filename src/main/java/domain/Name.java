package domain;

public class Name {

    private final String name;

    public Name(String name) {
        if (name.length() < 2 || name.length() > 10) {
            throw new IllegalArgumentException("플레이어의 이름은 2글자 이상 10글자 이하여야 합니다.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
