package blackjack.domain.game;

import java.util.function.BiConsumer;

public class Dealer extends Gamer {

    private static final int DRAWING_MAXIMUM = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    public void draw(final Deck deck, final BiConsumer<String, Integer> drawing) {
        while (isDrawable()) {
            draw(deck.pick());
            noticeDrawing(drawing);
        }
    }

    public double profit(final double playersTotalProfit) {
        return -playersTotalProfit;
    }

    public boolean isLowerScore(final Player player) {
        return sumOfCards() < player.sumOfCards();
    }

    public boolean isHigherScore(final Player player) {
        return sumOfCards() > player.sumOfCards();
    }

    private void noticeDrawing(final BiConsumer<String, Integer> drawing) {
        drawing.accept(name, DRAWING_MAXIMUM);
    }

    public PlayingCards openPartOfCards() {
        return state.partOfPlayingCards();
    }

    @Override
    public boolean isDrawable() {
        return state.cardTotal() <= DRAWING_MAXIMUM;
    }
}
