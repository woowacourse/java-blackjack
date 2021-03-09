package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import blackjack.domain.card.Deck;

import java.util.ArrayList;

public class Dealer extends Participant {
    
    private static final int DEALER_THRESHOLD = 16;
    
    private static final String NAME = "딜러";
    
    private final Deck deck;
    
    private Dealer(String name, CardHand cardHand, Deck deck) {
        super(name, cardHand);
        this.deck = deck;
    }
    
    public static Dealer create() {
        CardHand cardHand = new CardHand(new ArrayList<>());
        
        return new Dealer(NAME, cardHand, Deck.createShuffledDeck());
    }
    
    public void deal(Player player) {
        final Card card = deck.drawCard();
        player.receive(card);
    }
    
    public void draw() {
        cardHand.add(deck.drawCard());
    }
    
    @Override
    public boolean canReceive() {
        return sumCardHand() <= DEALER_THRESHOLD;
    }
    
    public Card getCard(int cardIndex) {
        return cardHand.get(cardIndex);
    }
}
