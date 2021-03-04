package blackjack.util;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Symbol;
import java.util.ArrayList;
import java.util.List;

public class CardFactory {

    private static final List<Card> normalCards = initNormalCards();

    public static List<Card> getNormalCards() {
        return normalCards;
    }

    private static List<Card> initNormalCards() {
        List<Card> cards = new ArrayList<>();

        for (Symbol symbol : Symbol.values()) {
            createCards(cards, symbol);
        }

        return cards;
    }

    private static void createCards(List<Card> cards, Symbol symbol) {
        for (CardNumber cardNumber : CardNumber.values()) {
            cards.add(new Card(symbol, cardNumber));
        }
    }
}
