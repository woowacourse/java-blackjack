package model.player;

import java.util.List;
import java.util.Objects;
import model.card.Card;
import model.card.Hand;

public class Player {

    private static final String INVALID_NAME_LENGTH = "플레이어 이름은 빈 값일 수 없습니다.";
    private static final int HIT_CONDITION = 22;

    private final String name;
    private final Hand hand;

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

    public Player hitCard(Card card) {
        Hand addedCards = hand.add(card);
        return new Player(name, addedCards);
    }

    public Player hitCards(List<Card> cards) {
        Hand addedCards = this.hand.addAll(cards);
        return new Player(name, addedCards);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
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
