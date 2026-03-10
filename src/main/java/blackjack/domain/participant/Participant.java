package blackjack.domain.participant;

import blackjack.domain.Card;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import java.util.List;

public class Participant {

    private static final int FIRST_DRAW_COUNT = 2;

    protected final String nickname;
    protected final Hand hand;

    protected Participant(String nickname) {
        this.nickname = nickname;
        this.hand = new Hand();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public String getNickname() {
        return nickname;
    }

    public void distributeCards(Deck deck) {
        List<Card> drewCards = deck.drawCards(FIRST_DRAW_COUNT);
        hand.addCard(drewCards);
    }

    public void addCardFrom(Deck deck) {
        List<Card> drewCards = deck.drawCard();
        hand.addCard(drewCards);
    }

    public boolean isDrawable() {
        return !isBusted();
    }

    public int getTotalScore() {
        return hand.getTotalScore();
    }

    public boolean isBusted() {
        return hand.isBusted();
    }
}
