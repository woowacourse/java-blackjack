package domain.participant;

public class Name {
    private final String name;

    public Name(String name) {
        isEmptyName(name);
        this.name = name;
    }

    private void isEmptyName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 이름은 공백이 불가합니다.");
        }
    }
}
