package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import java.util.List;

public class Participant {
    
    private static final int FIRST_DRAW_COUNT = 2;
    
    protected final String nickname;
    protected final Hand hand;
    
    protected Participant(String nickname) {
        this.nickname = nickname;
        this.hand = new Hand();
    }
    
    public void drawInitialCards(Deck deck) {
        List<Card> initialCards = deck.drawCards(FIRST_DRAW_COUNT);
        hand.addCards(initialCards);
    }
    
    public void drawCard(Deck deck) {
        Card card = deck.drawCard();
        hand.addCard(card);
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public List<Card> getCards() {
        return hand.getCards();
    }
    
    public boolean isDrawable() {
        return !isBusted();
    }
    
    public boolean isBusted() {
        return hand.isBusted();
    }
    
    public int getScore() {
        return hand.getTotalScore();
    }
}
