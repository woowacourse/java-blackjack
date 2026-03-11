package domain.pariticipant;

import constant.BlackjackConstant;
import domain.card.Card;
import domain.card.CardShuffler;
import domain.card.Deck;
import domain.card.Hand;

import java.util.List;

public abstract class Participant {
    private final Name name;

    private final Hand hand;

    protected Participant(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    public void drawInitialCards(Deck deck, CardShuffler cardShuffler) {
        for (int i = 0; i < BlackjackConstant.INIT_DRAW_COUNT; i++) {
            drawCard(deck, cardShuffler);
        }
    }

    protected void drawCard(Deck deck, CardShuffler cardShuffler) {
        int cardIndex = cardShuffler.getRandomCardIndex(deck.getDeckSize());
        hand.addCard(deck.draw(cardIndex));
    }

    public void addHandCard(Card card) {
        hand.addCard(card);
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }


    public int getScore() {
        return hand.getScore();
    }

    public String getName() {
        return name.name();
    }

    public List<Card> getHandCards() {
        return hand.getHands();
    }

    public int getHandSize() {
        return hand.getSize();
    }

}
