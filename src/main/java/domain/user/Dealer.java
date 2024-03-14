package domain.user;

import domain.Deck;
import domain.card.Card;
import java.util.List;

public class Dealer {
    private static final int MAX_ADDABLE = 16;
    private final Hand hand;

    public Dealer() {
        this.hand = new Hand();
    }

    public void addStartCards(Deck deck) {
        hand.addStartCards(deck.getNewCard(), deck.getNewCard());
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public int sumHand() {
        return hand.sumCard();
    }

    public boolean busted() {
        return hand.busted();
    }

    public boolean isBlackJack() {
        return hand.isBlackjack();
    }

    public boolean isAddable() {
        return hand.sumCard() <= MAX_ADDABLE;
    }

    public List<Card> getVisibleCard() {
        return List.of(hand.getFirstCard());
    }

    public List<Card> getAllCards() {
        return hand.getCards();
    }
}
