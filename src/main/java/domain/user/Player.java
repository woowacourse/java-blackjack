package domain.user;

import domain.Hand;
import domain.PlayerName;
import domain.card.Card;
import java.util.List;
import java.util.Objects;

public class Player {

    protected static final String DEALER_NAME = "딜러";
    public static final int INITIAL_HAND_COUNT = 2;
    protected final PlayerName name;
    protected Hand hand = Hand.valueOf(null);

    public Player(String name) {
        this.name = new PlayerName(name);
    }

    public void dealt(Card card) {
        hand = hand.add(card);
    }

    public List<Card> getInitialHand() {
        if (hand == null || hand.getSize() < INITIAL_HAND_COUNT) {
            throw new IllegalStateException("모든 플레이어는 카드 두장을 받고 시작해야 합니다.");
        }
        List<Card> card = hand.getAllCards();
        return List.of(card.get(0), card.get(1));
    }

    public List<Card> getHand() {
        return hand.getAllCards();
    }

    public String getName() {
        return name.getValue();
    }

    public int getPoint() {
        return hand.calculatePoint();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player that = (Player) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Player{" +
            "name='" + name + '\'' +
            ", hand=" + hand +
            '}';
    }
}

