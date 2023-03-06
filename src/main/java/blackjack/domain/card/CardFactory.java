package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CardFactory {

    public static List<Card> createShuffledCard() {
        List<Card> createdCard = new ArrayList<>();

        for (Symbol symbol : Symbol.values()) {
            addCards(createdCard, symbol);
        }
        Collections.shuffle(createdCard);

        return createdCard;
    }

    private static void addCards(List<Card> createdCard, Symbol symbol) {
        for (Number number : Number.values()) {
            createdCard.add(new Card(symbol, number));
        }
    }
}
