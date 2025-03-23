package blackjack.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Card {

    private static final Map<CardSymbol, Map<CardRank, Card>> CACHE = createCardCache();

    private final CardSymbol cardSymbol;
    private final CardRank cardRank;

    public Card(CardSymbol cardSymbol, CardRank cardRank) {
        this.cardSymbol = cardSymbol;
        this.cardRank = cardRank;
    }

    private static Map<CardSymbol, Map<CardRank, Card>> createCardCache() {
        Map<CardSymbol, Map<CardRank, Card>> cache = new HashMap<>();
        for (CardSymbol cardSymbol : CardSymbol.values()) {
            cache.put(cardSymbol, new HashMap<>());
            for (CardRank cardRank : CardRank.values()) {
                cache.get(cardSymbol).put(cardRank, new Card(cardSymbol, cardRank));
            }
        }
        return cache;
    }

    public static Card of(CardSymbol cardSymbol, CardRank cardRank) {
        return Optional.ofNullable(CACHE.get(cardSymbol))
                .map(rankMap -> rankMap.get(cardRank))
                .orElse(new Card(cardSymbol, cardRank));
    }

    public boolean isAce() {
        return cardRank == CardRank.ACE;
    }

    public CardRank getCardRank() {
        return cardRank;
    }

    public int getPoint() {
        return cardRank.getPoint();
    }

    public CardSymbol getCardSymbol() {
        return cardSymbol;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return cardSymbol == card.cardSymbol && cardRank == card.cardRank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardSymbol, cardRank);
    }
}
