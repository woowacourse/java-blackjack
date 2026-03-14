package blackjack.domain;

import java.util.List;
import java.util.Objects;

public class Player {
    private final Name name;
    private final Hand hand;

    private Player(Name name, Hand hand) {
        validate(name, hand);
        this.name = name;
        this.hand = hand;
    }

    private void validate(Name name, Hand hand) {
        Objects.requireNonNull(name, "플레이어 이름은 null일 수 없습니다.");
        Objects.requireNonNull(hand, "플레이어 덱(카드)은 null일 수 없습니다.");
    }

    public static Player of(Name name) {
        return new Player(name, Hand.init());
    }

    public void hit(TrumpCard card) {
        hand.add(card);
    }

    public boolean canHit() {
        return !isBust();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public int score() {
        return hand.calculateScore();
    }

    public String name() {
        return name.getName();
    }

    public List<String> cardNames() {
        return hand.cardNames();
    }
}
