package domain;

import java.util.List;

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
            throw new IllegalArgumentException("플레이어는 이름과 손패를 가져야합니다.");
        }
    }

    public List<TrumpCard> getCards() {
        return hand.getCards();
    }

    public void addCard(TrumpCard drawnCard) {
        hand.addCard(drawnCard);
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public int getTotalScore() {
        return hand.calculateTotalScore();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public boolean isBust() {
        return hand.isBust();
    }
}
