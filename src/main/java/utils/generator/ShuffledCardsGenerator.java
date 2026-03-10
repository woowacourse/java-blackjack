package utils.generator;

import domain.Card;
import domain.CardRank;
import domain.CardShape;
import domain.Cards;

import java.util.ArrayList;
import java.util.List;

public final class ShuffledCardsGenerator implements CardsGenerator {
    @Override
    public Cards generateShuffledCards(){
        Cards cards = generate();
        shuffleCards(cards);
        return cards;
    }

    public void shuffleCards(Cards cards){
        cards.shuffle();
    }

    private Cards generate() {
        List<Card> cards = new ArrayList<>();
        for (CardShape cardShape : CardShape.values()) {
            cards.addAll(createCardsFromRank(cardShape));
        }
        return new Cards(cards);
    }

    private List<Card> createCardsFromRank(CardShape cardShape) {
        List<Card> cards = new ArrayList<>();
        for (CardRank cardRank : CardRank.values()) {
            cards.add(new Card(cardShape, cardRank));
        }
        return cards;
    }


}
