package blackjack.domain;

import java.util.ArrayList;
import java.util.Objects;

public class Player {

    private final String name;
    private final Hand hand;

    public Player(String name) {
        validateName(name);
        this.name = name;
        this.hand = new Hand(new ArrayList<>());
    }

    private void validateName(String name) {
        if (name.trim().length() == 0) {
            throw new IllegalArgumentException("공백은 이름으로 사용할 수 없습니다.");
        }
    }

    public void drawCard(Deck deck) {
        hand.addCard(deck.draw());
    }

    public Hand getHand() {
        return hand;
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
}
