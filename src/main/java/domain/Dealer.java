package domain;

public class Dealer extends Gamer {

    private static final String NAME = "딜러";
    private static final int HIT_THRESHOLD = 16;

    public Dealer() {
        super(NAME);
    }

    @Override
    public void hit(Deck totalCards) {
        validateCanHit();
        add(totalCards);
    }

    private void validateCanHit() {
        if (getHand().calculateTotalScore() > HIT_THRESHOLD) {
            throw new IllegalStateException("[ERROR] 딜러의 점수가 16점을 초과하므로 카드를 뽑을 수 없습니다.");
        }
    }

}
