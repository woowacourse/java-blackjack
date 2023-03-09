package domain.user;

import domain.Hand;
import domain.PlayerName;
import domain.card.Card;
import java.util.List;

public class Player {

    protected static final int INITIAL_HAND_COUNT = 2;
    private static final Hand EMPTY_HAND = Hand.from(null);
    protected final PlayerName name;
    protected Hand hand = EMPTY_HAND;

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
}

