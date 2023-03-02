package domain;

public final class Participant extends Player {

    private Participant(final String name, final Cards cards) {
        super(name, cards);
    }

    public static Participant from(final String name) {
        return new Participant(name, new Cards());
    }
}
