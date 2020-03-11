package model;

import java.util.List;

public class TempDeck implements Strategy{

    private final List<Card> cards;

    public TempDeck(List<Card> cards){
        this.cards = cards;
    }
    @Override
    public Card draw() {
        return cards.get(0);
    }

    @Override
    public CardHand initialDraw() {
        CardHand cardHand = new CardHand();
        for (Card card : cards) {
            cardHand.addCard(card);
        }
        return cardHand;
    }
}
