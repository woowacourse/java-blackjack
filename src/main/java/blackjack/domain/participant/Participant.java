package blackjack.domain.participant;

import java.util.List;
import blackjack.domain.card.Card;

public abstract class Participant {

    private final ParticipantHand hand;
    private final ParticipantName name;

    protected Participant(String name) {
        this.hand = new ParticipantHand();
        this.name = new ParticipantName(name);
    }

    public void hit(Card card) {
        if (!isPlayable()) {
            throw new IllegalStateException("카드를 더이상 받을 수 없습니다.");
        }

        hand.add(card);
    }

    protected abstract boolean isPlayable();

    public int calculateScore() {
        return hand.calculateScore();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public boolean isBlackJackScore() {
        return hand.isBlackJackScore();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public String getName() {
        return name.getValue();
    }
}
