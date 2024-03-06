package domain;

public interface Participant {
    boolean isDrawable();

    void draw(final Deck deck);
}
