package blackjack.model.player;

public class Gamer extends Player {
    private static final String ERROR_BLANK = "[ERROR] 이름은 공백일 수 없습니다.";
    private static final String ERROR_MAX_LENGTH = "[ERROR] 이름은 15자 이하로 입력해주세요.";

    private static final int MAX_LENGTH = 15;

    private final String name;

    public Gamer(String name) {
        super();
        checkBlankIn(name);
        checkLengthOf(name);
        this.name = name;
    }

    private void checkBlankIn(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(ERROR_BLANK);
        }
    }

    private void checkLengthOf(String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(ERROR_MAX_LENGTH);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isImpossibleHit() {
        return cards.isOverLimitScore();
    }
}
