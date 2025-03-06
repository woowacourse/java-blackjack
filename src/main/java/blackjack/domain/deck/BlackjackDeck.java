package blackjack.domain.deck;

import blackjack.domain.card.Card;

import java.util.List;

public final class BlackjackDeck implements BlackjackCardHandInitializer, CardDrawer {
    
    private final Deck deck;
    
    public BlackjackDeck() {
        this.deck = new Deck();
    }
    
    @Override
    public List<Card> handoutInitialCards() {
        return List.of(draw(), draw());
    }
    
    @Override
    public Card draw() {
         return deck.draw();
    }
}
