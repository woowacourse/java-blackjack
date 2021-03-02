package blackjack;

public abstract class Gamer {
    private String name;
    private final Hands hands; // 초기 카드 두장

    public Gamer(String name, Hands hands) {
        this.name = name;
        this.hands = hands;
    }

    public void receiveCard(Card card) {
        hands.add(card);
    }

}
