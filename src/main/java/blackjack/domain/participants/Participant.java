package blackjack.domain.participants;

import blackjack.domain.cards.Card;
import blackjack.domain.cards.Deck;
import blackjack.domain.cards.Hand;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public abstract class Participant {

    public static final int STARTING_CARD_COUNT = 2;

    private final Hand hand;
    private final Name name;
    private ParticipantStatus status;

    public Participant(String name) {
        this.hand = new Hand(new ArrayList<>());
        this.name = new Name(name);
        this.status = ParticipantStatus.HIT;
    }

    protected abstract ParticipantStatus updateStatus(ParticipantStatus currentStatus);

    public void addCard(Card card) {
        if (isNotContinue()) {
            throw new IllegalStateException("더 이상 카드를 뽑을 수 없는 플레이어입니다.");
        }
        hand.addCard(card);
        status = updateStatus(status);
    }

    protected void setStatus(ParticipantStatus status) {
        this.status = status;
    }

    public boolean isContinue() {
        return status == ParticipantStatus.HIT;
    }

    public boolean isNotContinue() {
        return !isContinue();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public String getName() {
        return name.unwrap();
    }

    public int getScore() {
        return hand.getScore();
    }

    public List<Card> getHand() {
        return hand.unwrap();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
