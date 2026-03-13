package utils.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.Card;
import domain.CardRank;
import domain.CardShape;

public final class ShuffledCardsGenerator implements CardsGenerator {
    @Override
    public List<Card> generateShuffledCards() {
        List<Card> cards = generate();
        shuffleCards(cards);
        return cards;
    }

    public void shuffleCards(List<Card> cards) {
        Collections.shuffle(cards);
    }

    private List<Card> generate() {
        List<Card> cards = new ArrayList<>();
        for (CardShape cardShape : CardShape.values()) {
            cards.addAll(createCardsFromRank(cardShape));
        }
        return cards;
    }

    private List<Card> createCardsFromRank(CardShape cardShape) {
        List<Card> cards = new ArrayList<>();
        for (CardRank cardRank : CardRank.values()) {
            cards.add(new Card(cardShape, cardRank));
        }
        return cards;
    }


}
