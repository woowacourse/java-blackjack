package blackjackgame.domain.card;

import java.util.ArrayList;
import java.util.List;
import blackjackgame.view.ErrorMessage;

public class Cards {
    private static final int CARD_ON_TOP_INDEX = 0;

    private final List<Card> cards;

    public Cards(CardsGenerator cardsGenerator) {
        this.cards = cardsGenerator.generate();
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.NO_MORE_CARD.getMessage());
        }
        return cards.remove(CARD_ON_TOP_INDEX);
    }

    public List<Card> drawCards(int count) {
        List<Card> cards = new ArrayList<>();
        for (int cardCount = 0; cardCount < count; cardCount++) {
            cards.add(drawCard());
        }

        return cards;
    }
}
