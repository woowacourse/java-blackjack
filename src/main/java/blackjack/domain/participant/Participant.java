package blackjack.domain.participant;

import blackjack.domain.Card;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.dto.ParticipantStatus;
import java.util.List;

public abstract class Participant {
    
    private static final int FIRST_DRAW_COUNT = 2;
    protected final String nickname;
    protected final Hand hand;
    
    protected Participant(String nickname) {
        this.nickname = nickname;
        this.hand = new Hand();
    }
    
    public String getInfoSnapshot() {
        return hand.getSnapshot();
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public ParticipantStatus distributeCards(Deck deck) {
        List<Card> drewCards = deck.drawCards(FIRST_DRAW_COUNT);
        return receiveCard(drewCards);
    }
    
    public abstract boolean isDrawable();
    
    public ParticipantStatus receiveCard(List<Card> receivedCards) {
        hand.addCard(receivedCards);
        return new ParticipantStatus(this);
    }
    
    public int getTotalScore() {
        return hand.getTotalScore();
    }
    
    public boolean isBusted() {
        return hand.isBusted();
    }
    
    public ParticipantStatus getCardStatus() {
        return new ParticipantStatus(this);
    }
}
