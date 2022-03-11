package blackjack.domain.user;

public class Name {

    private final String name;

    public Name(String name) {
        validateExist(name);
        this.name = name;
    }

    private void validateExist(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(String.format("이름에 오류가 있습니다 : %s", name));
        }
    }

    public String getName() {
        return name;
    }
}
