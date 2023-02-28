package domain;

public final class Participant extends Player {

    private Participant(final Name name, final Cards cards) {
        super(name, cards);
    }

    public static Participant from(final Name name) {
        return new Participant(name, new Cards());
    }
}
