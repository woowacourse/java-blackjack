package blackjack.card.initializer;

import blackjack.card.Card;
import blackjack.card.CardRank;
import blackjack.card.CardSymbol;
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
        List<CardSymbol> cardSymbols = getSymbols();

        insertCards(cardRanks, cardSymbols, cards);

        return cards;
    }

    private void insertCards(List<CardRank> cardRanks, List<CardSymbol> cardSymbols, List<Card> cards) {
        for (CardRank cardRank : cardRanks) {
            for (CardSymbol cardSymbol : cardSymbols) {
                cards.add(Card.of(cardSymbol, cardRank));
            }
        }
    }

    private List<CardSymbol> getSymbols() {
        return Arrays.stream(CardSymbol.values()).toList();
    }

    private List<CardRank> getNumbers() {
        return Arrays.stream(CardRank.values())
                .toList();
    }
}
