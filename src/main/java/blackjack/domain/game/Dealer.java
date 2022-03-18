package blackjack.domain.game;

import blackjack.domain.card.Card;

import java.util.List;
import java.util.function.BiConsumer;

public class Dealer extends Gamer {

    public static final int DRAWING_MAXIMUM = 16;
    public static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    public void draw(final Deck deck, final BiConsumer<String, Integer> drawing) {
        while (isDrawable()) {
            drawCard(deck.pick());
            noticeDrawing(drawing);
        }
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

    public List<Card> openPartOfCards() {
        return playingCards.getPartOfCard();
    }

    @Override
    public boolean isDrawable() {
        return playingCards.calculateTotal() <= DRAWING_MAXIMUM;
    }
}
