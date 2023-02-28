package blackjack.domain;

public class Player {

    private final String name;

    public Player(String name) {
        validateSpace(name);
        validateLength(name);
        this.name = name;
    }

    private void validateLength(String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException("이름의 길이는 5자 이상일 수 없습니다.");
        }
    }

    private void validateSpace(String name) {
        if(name.isBlank() || name.contains(" ")) {
            throw new IllegalArgumentException("이름은 공백일 수 없습니다.");
        }
    }

}
