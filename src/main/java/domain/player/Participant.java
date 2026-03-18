package domain.player;

import domain.card.Card;
import domain.player.attribute.Hand;
import domain.player.attribute.Name;
import java.util.List;

public class Participant {

    protected static final int DEFAULT_START_CARD_COUNT = 2;

    protected final Name name;

    protected final Hand hand;

    public Participant(String name) {
        this.name = new Name(name);
        this.hand = new Hand();
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
        return new ParticipantGameInfo(name.getName(), hand.getInfo(), hand.calculateScore());
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }
}
