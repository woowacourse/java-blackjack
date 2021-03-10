package blackjack.domain.participants;

import blackjack.domain.cards.Card;
import blackjack.domain.cards.Hand;
import blackjack.domain.names.Name;
import blackjack.domain.state.finished.Blackjack;
import blackjack.domain.state.finished.Bust;
import blackjack.domain.state.running.Hit;
import blackjack.domain.state.State;
import blackjack.domain.state.hitstrategy.HitStrategy;
import java.util.List;
import java.util.Objects;

public class Participant {

    public static final int STARTING_CARD_COUNT = 2;

    private final Name name;
    private State state;

    public Participant(Name name, HitStrategy hitStrategy) {
        this.name = name;
        this.state = new Hit(new Hand(), hitStrategy);
    }

    public void draw(Card card) {
        if (isNotContinue()) {
            throw new IllegalStateException("더 이상 카드를 뽑을 수 없는 플레이어입니다.");
        }
        state = state.draw(card);
    }

    protected State getState() {
        return state;
    }

    protected void setState(State state) {
        this.state = state;
    }

    public boolean isContinue() {
        return !state.isFinished();
    }

    public boolean isNotContinue() {
        return !isContinue();
    }

    public boolean isBust() {
        return state instanceof Bust;
    }

    public boolean isBlackJack() {
        return state instanceof Blackjack;
    }

    public String getName() {
        return name.unwrap();
    }

    public int getScore() {
        return state.getScore();
    }

    public List<Card> getHand() {
        return state.getHand().unwrap();
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
