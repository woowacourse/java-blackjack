package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StandardCardsInitializer implements CardsInitializer {

    @Override
    public List<Card> initialize() {
        List<Card> cards = createCards();
        Collections.shuffle(cards);
        return cards;
    }

    private List<Card> createCards() {
        List<Card> cards = new ArrayList<>();

        List<CardRank> cardRanks = getNumbers();
        List<Symbol> symbols = getSymbols();

        insertCards(cardRanks, symbols, cards);

        return cards;
    }

    private void insertCards(List<CardRank> cardRanks, List<Symbol> symbols, List<Card> cards) {
        for (CardRank cardRank : cardRanks) {
            for (Symbol symbol : symbols) {
                cards.add(new Card(symbol, cardRank));
            }
        }
    }

    private List<Symbol> getSymbols() {
        return Arrays.stream(Symbol.values()).toList();
    }

    private List<CardRank> getNumbers() {
        return Arrays.stream(CardRank.values())
                .filter(number -> number != CardRank.SOFT_ACE)
                .toList();
    }
}
