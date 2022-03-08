package blackjack.domain;

public class Gamer {

    private static final String BANNED_GAMER_NAME = "딜러";

    private final String name;

    public Gamer(final String name) {
        checkBannedName(name);
        this.name = name;
    }

    private void checkBannedName(String name) {
        if (name.equals(BANNED_GAMER_NAME)){
            throw new IllegalArgumentException("[ERROR] Gamer의 이름은 딜러일 수 없습니다.");
        }
    }
}
