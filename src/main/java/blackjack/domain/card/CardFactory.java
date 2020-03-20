package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class CardFactory {

    public static List<Card> generate() {
        List<Card> cards = new ArrayList<>();

        for (CardSymbol symbol : CardSymbol.values()) {
            createByType(cards, symbol);
        }
        return cards;
    }

    private static void createByType(List<Card> cards, CardSymbol symbol) {
        for (CardType type : CardType.values()) {
            cards.add(new Card(symbol, type));
        }
    }
}
