package domain.user;

import java.util.function.Consumer;

import domain.card.Card;
import domain.card.Deck;

public class Dealer extends User {

    public static final String NAME = "딜러";
    private static final int PIVOT = 17;

    private Dealer() {
        super(NAME);
    }

    public static Dealer appoint() {
        return new Dealer();
    }

    public Card getFirstCard() {
        return cards.getFirstCard();
    }

    @Override
    protected boolean isAvailableToDraw() {
        return !cards.areBust() && !cards.areBlackJack() && !cards.areBlackJackPoint()
                && cards.calculatePointAccordingToHasAce() < PIVOT;
    }

    public void additionalDealOut(Deck deck, Consumer<String> showResult) {
        while (isAvailableToDraw()) {
            draw(deck);
            showResult.accept(NAME);
        }
    }
}
