package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Card {

    private static final Map<CardSymbol, Map<CardNumber, Card>> CARD_CACHE = new HashMap<>();
    private final CardNumber cardNumber;
    private final CardSymbol cardSymbol;

    static {
        for (SymbolCandidate symbolCandidate : SymbolCandidate.values()) {
            CardSymbol cardSymbol = CardSymbol.from(symbolCandidate.getSymbol());
            Map<CardNumber, Card> cardNumberCache = new HashMap<>();
            fillCardNumber(cardSymbol, cardNumberCache);
            CARD_CACHE.put(cardSymbol, cardNumberCache);
        }
    }

    private static void fillCardNumber(CardSymbol cardSymbol,
        Map<CardNumber, Card> cardNumberCache) {
        for (NumberCandidate numberCandidate : NumberCandidate.values()) {
            CardNumber cardNumber = CardNumber.from(numberCandidate.getNumber());
            Card card = new Card(cardNumber, cardSymbol);
            cardNumberCache.put(cardNumber, card);
        }
    }

    private Card(CardNumber number, CardSymbol symbol) {
        this.cardNumber = number;
        this.cardSymbol = symbol;
    }

    public static Card from(String cardRawNumber, String cardRawSymbol) {
        CardSymbol cardSymbol = CardSymbol.from(cardRawSymbol);
        CardNumber cardNumber = CardNumber.from(cardRawNumber);
        return CARD_CACHE.get(cardSymbol)
            .get(cardNumber);
    }

    public boolean isAce() {
        CardNumber aceNumber = CardNumber.from("A");
        if (cardNumber.equals(aceNumber)) {
            return true;
        }
        return false;
    }

    public static synchronized List<Card> getAllCard() {
        List<Card> allCard = new ArrayList<>();
        for (Map.Entry<CardSymbol, Map<CardNumber, Card>> cardSymbolCache : CARD_CACHE.entrySet()) {
            Map<CardNumber, Card> cardSymbols = cardSymbolCache.getValue();
            fillNumberCard(allCard, cardSymbols);
        }
        Collections.shuffle(allCard);
        return allCard;
    }

    private static void fillNumberCard(List<Card> allCard, Map<CardNumber, Card> cardSymbols) {
        for (Map.Entry<CardNumber, Card> oneCard : cardSymbols.entrySet()) {
            Card card = oneCard.getValue();
            allCard.add(card);
        }
    }

    public int getPoint() {
        return this.cardNumber.getValue();
    }

    public String getCard() {
        return cardNumber.getAlphabet() + cardSymbol.getSymbol();
    }
}
