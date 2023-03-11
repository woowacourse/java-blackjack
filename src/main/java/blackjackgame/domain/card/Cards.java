package blackjackgame.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final int CARD_ON_TOP_INDEX = 0;

    private final List<Card> cards;

    public Cards(CardsGenerator cardsGenerator) {
        this.cards = cardsGenerator.generate();
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("카드가 존재하지 않습니다.");
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
