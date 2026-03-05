package blackjack.model;

public class Dealer {
    private static final int PICK_THRESHOLD = 16;
    private static final int BLACKJACK_SCORE = 21;

    private final Hands hands;

    private Dealer(Hands hands) {
        if (hands == null) {
            throw new IllegalArgumentException("hands가 null입니다.");
        }

        this.hands = hands;
    }

    public static Dealer create() {
        return new Dealer(Hands.empty());
    }

    //카드 덱에서 카드를 한 장 뽑아서 핸즈에 추가한다.
    public void pickACard(CardDeck cardDeck) {
        hands.addACard(cardDeck.draw());
    }

    // 16점을 초과하면 false를 반환한다.
    public boolean canPick() {
        return !hands.isTotalScoreOver(PICK_THRESHOLD);
    }

    //핸즈의 총 점수가 21 초과이면 true를 반환한다.
    public boolean isBust() {
        return hands.isTotalScoreOver(BLACKJACK_SCORE);
    }
}
