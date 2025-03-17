package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public final class Cards {
    
    private final List<Card> cards;
    
    private Cards(final List<Card> cards) {
        this.cards = cards;
    }
    
    public static Cards createTrumpCards() {
        List<Card> cards = new ArrayList<>();
        for (CardShape shape : CardShape.values()) {
            for (CardNumber number : CardNumber.values()) {
                cards.add(new Card(number, shape));
            }
        }
        return new Cards(cards);
    }
    
    public List<Card> getCards() {
        return this.cards;
    }
}
