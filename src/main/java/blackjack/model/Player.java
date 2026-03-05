package blackjack.model;

public class Player {

    private static final int BLACKJACK_SCORE = 21;

    private final String name;
    private final Hands hands;

    private Player(String name, Hands hands) {
        this.name = name;
        this.hands = hands;
    }

    public static Player of (String name) {
        return new Player(name, Hands.empty());
    }

    public void pickACard(CardDeck cardDeck) {
        hands.addACard(cardDeck.draw());
    }

    //핸즈의 총 점수가 21 초과이면 true를 반환한다.
    public boolean isBust() {
        return hands.isTotalScoreOver(BLACKJACK_SCORE);
    }

    public int getHandsTotalScore() {
        return hands.calculateTotalScore();
    }
}
