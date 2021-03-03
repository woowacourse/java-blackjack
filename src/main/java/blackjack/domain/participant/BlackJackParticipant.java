package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.Hand;
import java.util.ArrayList;
import java.util.Objects;

public abstract class BlackJackParticipant {
    private final Hand hand;
    private boolean hit;
    private final String name;

    public BlackJackParticipant(String name) {
        validateName(name);
        this.hand = new Hand(new ArrayList<>());
        this.hit = true;
        this.name = name;
    }

    private void validateName(String name) {
        if (name.trim().length() == 0) {
            throw new IllegalArgumentException("공백은 이름으로 사용할 수 없습니다.");
        }
    }

    abstract public void drawCard(Deck deck);

    public int getScore() {
        return hand.getScore();
    }

    public Hand getHand() {
        return hand;
    }

    public boolean isContinue() {
        return hit;
    }

    public String getName() {
        return name;
    }

    protected void cannotDraw() { this.hit = false;}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BlackJackParticipant that = (BlackJackParticipant) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
