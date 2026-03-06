package blackjack.model;

import java.util.List;

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

    // 딜러가 초기 2장의 카드를 받는다.
    public void pickInitCards(CardDeck cardDeck) {
        hands.addCard(cardDeck.pick());

        Card secondPickedCard = cardDeck.pick();
        secondPickedCard.flip();
        hands.addCard(secondPickedCard);
    }

    // 16점을 초과하면 false를 반환한다.
    public boolean canPick() {
        return !hands.isTotalScoreOver(PICK_THRESHOLD);
    }

    //핸즈의 총 점수가 21 초과이면 true를 반환한다.
    public boolean isBust() {
        return hands.isTotalScoreOver(BLACKJACK_SCORE);
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

    // 패에서 앞 면인 카드만 가져온다.
    public List<Card> getOpenedCards() {
        return hands.getOpenedCards();
    }

    // 모든 카드를 가져온다.
    public List<Card> getAllCard() {
        return hands.getAllCard();
    }

    public int getCurrentTotalScore() {
        return hands.calculateTotalScore();
    }

    public void pickAdditionalCard(CardDeck cardDeck) {
        hands.addCard(cardDeck.pick());
    }
}
