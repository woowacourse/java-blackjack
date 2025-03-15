package participant;

import card.Card;
import game.Playable;
import java.util.List;

public class Dealer implements Playable {

    private static final int DEALER_CARDS_MIN_TOTAL = 17;

    private final Participant participant;

    public Dealer() {
        this.participant = new Participant("딜러");
    }

    @Override
    public void receiveCard(Card card) {
        participant.receiveCard(card);
    }

    @Override
    public boolean canReceiveCard() {
        return score() < DEALER_CARDS_MIN_TOTAL;
    }

    @Override
    public boolean isBlackjack() {
        return participant.isBlackjack();
    }

    @Override
    public boolean isBust() {
        return participant.isBust();
    }

    @Override
    public int score() {
        return participant.score();
    }

    @Override
    public String getNickname() {
        return participant.getNickname();
    }

    @Override
    public List<Card> getCards() {
        return participant.getCards();
    }

    public Card getLastCard() {
        return getCards().getLast();
    }
}
