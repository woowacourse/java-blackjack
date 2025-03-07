package domain.participant;

import domain.card.Card;
import domain.card.CardDeck;
import java.util.List;

public class Dealer implements Participant {

    private final CardDeck cardDeck;

    public Dealer() {
        this.cardDeck = new CardDeck();
    }

    public Card getOpenCard() {
        return cardDeck.getCards().getFirst();
    }

    @Override
    public void setUpCardDeck(Card first, Card second) {
        cardDeck.setUpCards(first, second);
    }

    @Override
    public boolean canTakeMoreCard(){
        return (calculateScore() <= 16);
    }

    @Override
    public void takeMoreCard(Card card) {
        cardDeck.takeMore(card);
    }

    @Override
    public int calculateScore() {
        return cardDeck.calculateScore();
    }

    @Override
    public List<Card> getCards() {
        return cardDeck.getCards();
    }
}
