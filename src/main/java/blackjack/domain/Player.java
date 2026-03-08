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

    public void receiveCards(List<TrumpCard> cards) {
        for (TrumpCard card : cards) {
            hand.receive(card);
        }
    }

    public void receiveCard(TrumpCard card) {
        hand.receive(card);
    }

    public int countCards() {
        return hand.countCards();
    }

    public int score() {
        return hand.calculateScore();
    }

    public String name() {
        return name.getName();
    }

    public boolean canHit() {
        return score() <= 21;
    }

    public boolean isBust() {
        return score() > 21;
    }

    public List<TrumpCard> getCards() {
        return hand.getCards();
    }
}
