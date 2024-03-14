package domain.participant;

import domain.playingcard.Deck;
import domain.playingcard.PlayingCard;

import java.util.List;
import java.util.stream.IntStream;

public abstract class Participant {
    private static final int INIT_DRAW_COUNT = 2;

    protected final Hand hand;

    protected Participant(final Hand hand) {
        this.hand = hand;
    }

    public abstract boolean isDrawable();

    protected static void initDraw(final Deck deck, final Hand hand) {
        IntStream.range(0, INIT_DRAW_COUNT)
                .forEach(i -> hand.addCard(deck.drawn()));
    }

    public void draw(final Deck deck) {
        hand.addCard(deck.drawn());
    }

    public Score getTotalScore() {
        return hand.getTotalScore();
    }

    public List<PlayingCard> getHandCards() {
        return hand.getPlayingCards();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }
}
