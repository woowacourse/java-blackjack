package domain.card;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CardPool {
    static final Map<CardType, Map<CardName, Card>> pool = new ConcurrentHashMap<>();

    static {
        for (CardType cardType : CardType.values()) {
            pool.put(cardType, new ConcurrentHashMap<>());
            for (CardName cardName : CardName.values()) {
                pool.get(cardType).put(cardName, new Card(cardName, cardType));
            }
        }
    }

    static List<Card> allCards() {
        return pool.keySet().stream()
                .flatMap(cardType -> pool.get(cardType).values().stream())
                .toList();
    }

    public static Card get(CardType cardType, CardName cardName) {
        return pool.get(cardType).get(cardName);
    }
}
