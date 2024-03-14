package domain.gamer;

public class Player extends Gamer {

    private static final int MAX_BLACK_JACK_SCORE = 21;
    private static final int BUST = 0;
    private final Name name;

    public Player(Name name) {
        this.name = name;
    }

    public boolean isDrawable() {
        return cards.getScoreByAceToOne() <= MAX_BLACK_JACK_SCORE && cards.countMaxScore() != BUST;
    }

    public Name getName() {
        return name;
    }
}
