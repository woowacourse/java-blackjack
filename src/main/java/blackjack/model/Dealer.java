package blackjack.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void pickCard(CardDeck cardDeck) {
        hands.addCards(cardDeck.draw(1));
    }

    // 16점을 초과하면 false를 반환한다.
    public boolean canPick() {
        return !hands.isTotalScoreOver(PICK_THRESHOLD);
    }

    //핸즈의 총 점수가 21 초과이면 true를 반환한다.
    public boolean isBust() {
        return hands.isTotalScoreOver(BLACKJACK_SCORE);
    }

    // 전체 결과 비교
    public TotalResult computeResult(List<Player> players) {
        Map<Player, Result> playerResults = new HashMap<>();
        return null;
    }

    // 플레이어와 딜러의 숫자를 비교한다.
    public Result compare(Player player) {
        // 플레이어가 버스트되면 무조건 딜러 승
        if (player.isBust()) {
            return Result.WIN;
        }

        // 플레이어가 버스트 되지 않고, 딜러가 버스트 되면 플레이어 승
        if (this.isBust()) {
            return Result.LOSE;
        }

        // 딜러의 점수가 더 높으면 승
        if (this.hands.isTotalScoreOver(player.getHandsTotalScore())) {
            return Result.WIN;
        }

        // 플레이어 점수가 더 높으면 패
        if (player.getHandsTotalScore() > this.hands.calculateTotalScore()) {
            return Result.LOSE;
        }

        // 무승부
        return Result.DRAW;
    }

    public Hands getHands() {
        return hands;
    }

    public List<Card> getCards(int count) {
        return hands.getCards(count);
    }

    public List<Card> getAllCard() {
        return hands.getAllCard();
    }

    public int getCurrentTotalScore() {
        return hands.calculateTotalScore();
    }
}
