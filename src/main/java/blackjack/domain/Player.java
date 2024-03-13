package blackjack.domain;

public class Player {
    private final String name;
    private final Hand hand;

    public Player(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public Score calculate() {
        return hand.calculate();
    }

    public boolean isBlackJack() { // -> 블랙잭인 경우 Stand 상태를 가짐
        return hand.isBlackJack();
    }

    public boolean isBust() { // -> 버스트인 경우 Bust 상태를 가짐
        return hand.isBust();
    }

    public boolean isPlayable() { // -> 버스트도 아니고 블랙잭도 아닌 경우 Hit 상태를 가짐
        return hand.isNotBust() && !isBlackJack();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }
}
