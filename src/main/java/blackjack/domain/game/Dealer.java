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

    public void draw(final CardDeck cardDeck, final BiConsumer<String, Integer> biConsumer) {
        while (isDrawable()) {
            drawCard(cardDeck.pick());
            noticeDrawing(biConsumer);
        }
    }

    public boolean isLowerScore(final Player player) {
        return sumOfCards() < player.sumOfCards();
    }

    public boolean isHigherScore(final Player player) {
        return sumOfCards() > player.sumOfCards();
    }

    private void noticeDrawing(final BiConsumer<String, Integer> biConsumer) {
        biConsumer.accept(name, DRAWING_MAXIMUM);
    }

    public List<Card> openPartOfCards() {
        return playingCards.getPartOfCard();
    }

    @Override
    public boolean isDrawable() {
        return playingCards.calculateTotal() <= DRAWING_MAXIMUM;
    }
}
