package domain.card;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Card {

    private final CardName cardName;
    private final CardType cardType;

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

    public static Card getInstanceFromPool(CardType cardType, CardName cardName) {
        Map<CardName, Card> cardNamePool = pool.get(cardType);
        return cardNamePool.get(cardName);
    }

    private Card(CardName cardName, CardType cardType) {
        this.cardName = cardName;
        this.cardType = cardType;
    }

    public CardName cardName() {
        return cardName;
    }

    public CardType cardType() {
        return cardType;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardName=" + cardName +
                ", cardType=" + cardType +
                '}';
    }
}
