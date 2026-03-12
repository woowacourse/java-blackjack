package blackjack.domain;


public class Player extends Participant {
    private static final int BLACKJACK_SCORE = 21;

    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isBlackjack() {
        return calculateTotalScore() == BLACKJACK_SCORE;
    }

}
