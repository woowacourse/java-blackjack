package domain;

public interface Participant {
    boolean isDrawable();

    void draw(final Deck deck);

    default boolean isDealer() {
        return this instanceof Dealer;
    }
}
