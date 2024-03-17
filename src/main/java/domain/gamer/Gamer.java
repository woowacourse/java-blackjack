package domain.gamer;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.Cards;
import java.util.List;

public class Gamer {

    protected final Cards cards;
    private final CardDeck cardDeck;

    public Gamer() {
        this.cards = new Cards();
        this.cardDeck = new CardDeck();
    }

    public void receiveCard() {
        Card card = cardDeck.pickCard();
        cards.addCard(card);
    }

    public void receiveCard(Card card) {
        cards.addCard(card);
    }

    public void initCard() {
        receiveCard();
    }

    public int getMaxGameScore() {
        return cards.countMaxScore();
    }

    public List<String> getCardStatus() {
        return cards.getCardsName();
    }
}
