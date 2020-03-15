package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class CardFactory {

    public static List<Card> generate() {

        List<Card> cards = new ArrayList<>();

        for (CardSymbol symbol : CardSymbol.values()) {
            addCard(cards, symbol);
        }
        return cards;
    }

    private static void addCard(List<Card> cards, CardSymbol symbol) {
        for (CardType type : CardType.values()) {
            cards.add(new Card(symbol, type));
        }
    }
}
