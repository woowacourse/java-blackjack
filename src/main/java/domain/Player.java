package domain;

public class Player {

    private final String name;
    private final Hand hand;

    public Player(String name, Hand hand) {
        validate(name, hand);
        this.name = name;
        this.hand = hand;
    }

    private void validate(String name, Hand hand) {
        validateNotNull(name, hand);
    }

    private void validateNotNull(String name, Hand hand) {
        if (name == null || name.isBlank() || hand == null) {
            throw new IllegalStateException("플레이어는 이름과 손패를 가져야합니다.");
        }
    }

    public void receiveCard(TrumpCard card) {
        hand.addCard(card);
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }
}
