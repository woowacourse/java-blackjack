package blackjack.model.participant;

import blackjack.model.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Player {

    private final String name;
    private final Hand hand;

    public Player(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public static Player from(String name) {
        validateName(name);
        return new Player(name, new Hand(new ArrayList<>()));
    }

    private static void validateName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름은 한글자 이상이어야합니다.");
        }
    }

    public boolean canHit() {
        return !hand.isBust() && !hand.isBlackjack();
    }

    public void receiveHand(Card card) {
        hand.receiveHand(card);
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public int calculateHandTotal() {
        return hand.calculateHandTotal();
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
        return Objects.hashCode(name);
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand.getHand());
    }
}
