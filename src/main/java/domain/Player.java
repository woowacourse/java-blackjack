package domain;

public class Player {

    private final String name;
    private final Hand hand;

    public Player(String name) {
        validate(name);
        this.name = name;
        this.hand = new Hand();
    }

    private void validate(String name) {
        validateNotBlank(name);
        validatePlayerNameLength(name);
    }

    private void validatePlayerNameLength(String name) {
        if (!(2 <= name.length() && name.length() <= 5)) {
            throw new IllegalArgumentException("[ERROR] 게임 참가자의 이름은 2~5글자 사이여야 합니다.");
        }
    }

    private void validateNotBlank(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 게임 참가자의 이름은 공백이 될 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }

}
