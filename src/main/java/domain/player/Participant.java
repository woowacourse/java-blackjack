package domain.player;

import static util.Constants.BLACKJACK_NUMBER;

import domain.card.Card;
import domain.player.attribute.Hand;
import domain.player.attribute.Name;
import java.util.List;

public class Participant {

    protected final Name name;

    protected Hand hand;

    public Participant(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public int getTotalScore() {
        return hand.calculateScore();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackjack() {
        return hand.getCardsSize() == 2 && getTotalScore() == BLACKJACK_NUMBER;
    }

    public String getName() {
        return name.getName();
    }

    public int getCardSize() {
        return hand.getCardsSize();
    }

    public List<String> getHandInfo() {
        return hand.getInfo();
    }

    public ParticipantGameInfo getParticipantGameInfo() {
        return new ParticipantGameInfo(
                name.getName(),
                hand.getInfo(),
                hand.calculateScore()
        );
    }

    public boolean isEqualName(String name) {
        if(this.name.isEqualName(name)) return true;
        return false;
    }
}
