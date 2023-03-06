package domain.card;

import java.util.ArrayList;
import java.util.List;
import view.ErrorMessage;

public class Cards {
    private static final int CARD_ON_TOP_INDEX = 0;

    private final List<Card> cards;

    public Cards(CardsGenerator shuffledCardsGenerator) {
        this.cards = shuffledCardsGenerator.generate();
    }


    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.NO_MORE_CARD.getMessage());
        }
        return cards.remove(CARD_ON_TOP_INDEX);
    }

    public List<Card> drawTwoCards() {
        List<Card> firstTurnCards = new ArrayList<>();
        firstTurnCards.add(drawCard());
        firstTurnCards.add(drawCard());

        return firstTurnCards;
    }
}
