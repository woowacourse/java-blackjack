package domain;

public class Dealer extends Gamer {

    private static final String NAME = "딜러";
    private static final int HIT_THRESHOLD = 16;

    public Dealer() {
        super(NAME);
    }

    @Override
    public void hit(CardDeck deck) {
        validateHitState();
        addFrom(deck);
    }

    private void validateHitState() {
        if (getHand().calculateTotalPoint() > HIT_THRESHOLD) {
            throw new IllegalStateException("딜러는 카드를 뽑을 수 없는 상태입니다.");
        }
    }

}
