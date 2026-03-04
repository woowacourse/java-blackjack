package blackjack.domain;

public class User {

    private final String name;

    public User(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException("유저 이름은 5자가 넘을 수 없습니다.");
        }
    }

}
