package domain.user;

import domain.card.Card;
import domain.card.Denomination;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Player {

    protected static final String DEALER_NAME = "딜러";
    protected final String name;
    protected final List<Card> hand = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public void dealt(Card card) {
        hand.add(card);
    }

    public List<Card> faceUpInitialHand() {
        if (hand.size() < 2) {
            throw new IllegalStateException("모든 플레이어는 카드 두장을 받고 시작해야 합니다.");
        }
        return List.of(hand.get(0), hand.get(1));
    }

    public List<Card> showHand() {
        return Collections.unmodifiableList(hand);
    }

    public int calculatePoint() {
        int point = 0;
        for (Card card : hand) {
            point += card.getDenomination().getPoint();
        }
        if (hasAce()) {
            return calculateAce(point);
        }
        return point;
    }

    protected int calculateAce(int point) {
        if (point + 10 <= 21) {
            return point + 10;
        }
        return point;
    }

    public String getName() {
        return name;
    }

    public boolean isPlayer() {
        return !name.equals(DEALER_NAME);
    }

    public boolean hasAce() {
        return hand.stream().anyMatch((card) -> card.getDenomination() == Denomination.ACE);
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
}

