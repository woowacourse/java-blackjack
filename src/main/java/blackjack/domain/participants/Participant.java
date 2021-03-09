package blackjack.domain.participants;

import blackjack.domain.cards.Card;
import blackjack.domain.cards.Hand;
import blackjack.domain.names.Name;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Participant {

    public static final int STARTING_CARD_COUNT = 2;

    private final Hand hand;
    private final Name name;
    private ParticipantState state;

    public Participant(Name name) {
        this.hand = new Hand(new ArrayList<>());
        this.name = name;
        this.state = ParticipantState.HIT;
    }

    protected abstract ParticipantState updateStatus(ParticipantState currentStatus);

    public void draw(Card card) {
        if (isNotContinue()) {
            throw new IllegalStateException("더 이상 카드를 뽑을 수 없는 플레이어입니다.");
        }
        hand.addCard(card);
        state = updateStatus(state);
    }

    protected void setState(ParticipantState state) {
        this.state = state;
    }

    public boolean isContinue() {
        return state == ParticipantState.HIT;
    }

    public boolean isNotContinue() {
        return !isContinue();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
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
