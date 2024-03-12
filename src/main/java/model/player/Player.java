package model.player;

import java.util.List;
import model.card.Card;
import model.card.Hand;

public class Player {

    private static final String INVALID_NAME_LENGTH = "플레이어 이름은 빈 값일 수 없습니다.";
    private static final int HIT_CONDITION = 22;

    private final String name;
    private Hand hand;

    public Player(String name) {
        this(name, new Hand(List.of()));
    }

    public Player(String name, Hand hand) {
        validateEmptyName(name);
        this.name = name;
        this.hand = hand;
    }

    private void validateEmptyName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH);
        }
    }

    public boolean isPossibleHit() {
        int totalNumbers = hand.calculateTotalNumbers();
        return totalNumbers < HIT_CONDITION;
    }

    public void hitCard(Card card) {
        hand = hand.add(card);
    }

    public void hitCards(List<Card> cards) {
        hand = hand.addAll(cards);
    }

    public int handSize() {
        return hand.size();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }
}
