package blackjack.domain;

public class Player extends Participant {

    private static final int MIN_NAME_LENGTH = 1;
    private static final int MAX_NAME_LENGTH = 5;

    private final String name;

    public Player(String name) {
        super(Cards.generateEmptyCards());
        validateNameLength(name);
        this.name = name;
    }

    private void validateNameLength(final String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 이름 길이는 최소 1글자에서 최대 5글자 입니다.");
        }
    }

    public int calculateTotalScore() {
        return this.cards.calculateTotalScore();
    }

    public String getName() {
        return name;
    }
}
